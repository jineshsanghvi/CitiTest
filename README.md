Run PRG1

Select b.position , sum(a.trade_value)
from tableA a
inner join tableB b on (a.business_date = b.business_date)
group by  b.position

1. code location - src\main\java\com\jinesh\test\PRG1\PRG1.java
2. It uses 2 beans TabA.java and TabB.java, both in the same folder as PRG1.java
3. Objects of TabA are loaded from src\main\resources\com\jinesh\test\PRG1\prg1-tabA-input.txt
        This file is delimited - trade_value, business_date
4. Similarly objects of TabB are loaded from src\main\resources\com\jinesh\test\PRG1\prg1-tabB-input.txt
        This file is delimited - position, business_date
5. Run PRG1 and it will read these 2 input files, simulate query and print results.

#######################################################################################

Run PRG2

SELECT a.position, a.security, a.identifier, qty/total_qty as ratio
FROM  table1  a
JOIN
(
    SELECT security, position, business_date total_qty = SUM( cast(qty as float) )
    FROM  table1
    GROUP BY position, security, business_date
)aa ON aa.security = a.security AND aa.position = a.position
WHERE a.business_date=aa.business_date

The query provided was missing group by on business_date in the join table aa. because of which the WHERE clause was incorrect.

ASSUMPTIONS
1. I HAVE ASSUMED, that business_date is on the GROUP BY and SELECT clause for the join table aa.
2. All columns have non null values.

1. code location - src\main\java\com\jinesh\test\PRG2\PRG2.java
2. It uses bean Table1.java which maps all the fields in the query and is located in the same folder.
3. Objects of Table1 are loaded from src\main\resources\com\jinesh\test\PRG2\prg2-input.txt
        This file is delimitd - Security, Position, Identifier, qty, businessDate
4. Run PRG2 and it will read this input date file, create objects, simulate query and print results.