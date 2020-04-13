package com.jinesh.test.PRG1;

import com.jinesh.test.FileReader;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Jinesh.Sanghvi on 04/12/2020.
 */
public class PRG1
{
    List<TabA> aList ;
    List<TabB> bList ;

    public PRG1(List<TabA> tabAList, List<TabB> tabBList) {
        this.aList = tabAList;
        this.bList = tabBList ;
    }

    /*
    Select b.position , sum(a.trade_value)
    from tableA a
    inner join tableB b on (a.business_date = b.business_date)
    group by  b.position
    */
    public void simulateQuery() {
        aList.stream()
                .filter(aRecord -> aRecord.getBd() != null)
                .flatMap(aRecord -> bList.stream()
                                        .filter(bRecord -> aRecord.getBd().equals(bRecord.getBd()))
                                        .map(bRecord -> new TABResult(bRecord.getPosition(), aRecord.getValue()))
                )
                //.forEach(r -> log.info("r {}", r));
                .collect(
                        Collectors.groupingBy(TABResult::getPosition, HashMap::new, Collectors.reducing(0, TABResult::getValue, (i1, i2) -> i1+i2))
                )
                .forEach( (k, v) -> System.out.format("position %s sum trade_value %s\n", k, v)) ;
    }

    public static void main(String[] args)
    {
        List<TabA> tabAList = null ;
        List<TabB> tabBList = null ;
        try{
            tabAList = readTabAFromfile();
            tabBList = readTabBFromfile();
        }catch(Exception e){
            System.out.println("Error reading file " + e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }

        PRG1 prg1 = new PRG1(tabAList, tabBList) ;
        prg1.simulateQuery();
    }

    private static List<TabA> readTabAFromfile() throws Exception{
        // File format - trade_value , business_date
        Function<String[], TabA> parser = (parts) -> new TabA( Integer.valueOf(parts[0]), parts[1] ) ;
        return FileReader.readFile( PRG1.class.getResource("prg1-tabA-input.txt").toURI() , 2, parser ) ;
    }

    private static List<TabB> readTabBFromfile() throws Exception{
        // File format - position , business_date
        Function<String[], TabB> parser = (parts) -> new TabB( parts[0], parts[1] ) ;
        return FileReader.readFile( PRG1.class.getResource("prg1-tabB-input.txt").toURI() , 2, parser ) ;
    }
}