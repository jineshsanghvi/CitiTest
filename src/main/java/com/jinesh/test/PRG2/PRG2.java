package com.jinesh.test.PRG2;

import com.jinesh.test.FileReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Jinesh.Sanghvi on 04/12/2020.
 */
public class PRG2
{
    List<Table1> table1List ;

    public PRG2(List<Table1> table1List){
        this.table1List = table1List;
    }

    /*
        SELECT a.position, a.security, a.identifier, qty/total_qty as ratio
        FROM  table1  a
        JOIN
        (
            SELECT security, position, business_date total_qty = SUM( cast(qty as float) )
            FROM  table1
            GROUP BY position, security, business_date
        )aa ON aa.security = a.security AND aa.position = a.position
        WHERE a.business_date=aa.business_date
    */
    public void simulateQuery(){
        // totalQtyMap has the total quantity for a given group of position, security and business_date
        final Map<PositionSecurityBusinessDateKey, BigDecimal> totalQtyMap = table1List.stream()
                .collect(
                        Collectors.groupingBy(Table1::getPosition,
                        HashMap::new,
                        Collectors.groupingBy(
                                Table1::getSecurity,
                                HashMap::new,
                                Collectors.groupingBy(
                                        Table1::getBusinessDate,
                                        HashMap::new,
                                        Collectors.reducing(0.0f, (t) -> t.getQty().floatValue() , (qty1, qty2) -> qty1 + qty2))
                                )
                        )
                )
                .entrySet()
                .stream()
                .collect(
                        HashMap::new,
                        (m, e1) -> {
                            e1.getValue()
                              .entrySet()
                              .stream()
                              .forEach(e2 -> {
                                  String position = e1.getKey();
                                  String security = e2.getKey();
                                  e2.getValue()
                                    //.entrySet()
                                    .forEach( (k, v) -> {
                                      Date business_date = k;
                                      float total = v ;
                                      m.put(new PositionSecurityBusinessDateKey(position, security, business_date) , new BigDecimal(total) );
                                  });
                              });

                        },
                        HashMap::putAll
                )
                ;

        System.out.println("verify totalQtyMap");
        totalQtyMap.forEach((k , v) -> System.out.format("%s : Total %s\n", k, v));

        // ratioMap has the final query result, with each input record as its key and the Ratio as its value
        Map<Table1, BigDecimal> ratioMap = this.table1List
                .stream()
                .collect(
                        HashMap::new,
                        (m, e1) -> {
                            // to avoid key object creation, this can be declared as final outside and then setter methods to be called to set the key data
                            PositionSecurityBusinessDateKey key = new PositionSecurityBusinessDateKey(e1.getPosition(), e1.getSecurity(), e1.getBusinessDate()) ;
                            m.put(e1, e1.getQty().divide( totalQtyMap.get(key), 2, RoundingMode.HALF_UP)  );
                        },
                        HashMap::putAll
                ) ;

        assert table1List.size() == ratioMap.size() ;   // this should match.

        System.out.println("\nQuery result with Ratio") ;
        ratioMap.forEach( (k, v) -> System.out.format("%s , ratio %s\n" , k, formatBD(v) ));
    }

    public static void main(String[] args)
    {
        List<Table1> list = null;
        try{
            list = getDataFromFile() ;
        }catch(Exception e) {
            System.out.println("Error reading file " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }

        PRG2 prg2 = new PRG2(list) ;
        prg2.simulateQuery();
    }

    private String formatBD(BigDecimal bd){
        DecimalFormat df = new DecimalFormat("#0.##") ;
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);

        return df.format(bd) ;
    }

    private static List<Table1> getDataFromFile() throws Exception
    {
        Function<String[], Table1> parser = (parts) -> new Table1(parts[0],parts[1], parts[2], parts[3],parts[4]) ;
        List<Table1> list =  FileReader.readFile( PRG2.class.getResource("prg2-input.txt").toURI() , 5, parser ) ;

        /*
        List<Table1> list = new LinkedList<>() ;

        // File format - Security, Position, Identifier, qty, businessDate
        List<String> lines = Files.readAllLines(Paths.get(PRG2.class.getResource("prg2-input.txt").toURI()) ) ;

        String[] parts ;
        for(String line : lines){
            if(line.startsWith("#"))
                continue;
            parts = line.split("\\s*,\\s*");
            if(parts.length != 5)
                throw new Exception("Incorrect file format. found " + parts.length + " parts") ;

            list.add(new Table1(parts[0],parts[1], parts[2], parts[3],parts[4]) ) ;
        }
        */

        //Verify file contents
        //list.forEach(System.out::println);
        return list ;
    }
}
