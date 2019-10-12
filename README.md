# at-myloanservice
Boilerplate for screening test

1. Create API : /createloan
**Where the ids are sequentially generatted by having another collection which saves the incremented values.
**Instead of ObjectIDs as default ids, the sequential Ids are being used.

2. Read API : /getpaginatedloans/{pageindex}/{pagesize}
Query takes two parameters. One is the the index of the page and the other one is how many records to display.
** During this operation another field called DPD is being created on the fly by comparing todays date and due date of the loan.
** If due date is null DPD is being set as 0.

Following are the main resources that were used while doing the tasks : 
https://docs.spring.io/spring-data/data-document/docs/current/reference/html/#mongodb-template-query
https://beginnersbook.com/2017/10/java-8-calculate-days-between-two-dates/
