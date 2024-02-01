# Simple Money Transfer Spring Boot Application

This is simple spring boot appplication for creding, debitng and transferring money. It has below services
1. /addCustomer -> Add customer in a bank
2. /addAccount -> Add account for that customer, one customer can have multiple accounts.
3. /getCustomer -> Get details of customer.
4. /creditDebit -> To credit or debit money from a particular account.
5. /trasnfer -> To transfer money from one account to another account.

**Relation between customer (account holder) and account**

<img width="383" alt="image" src="https://github.com/ajitp14/banking/assets/108980419/50dfabf0-d5ad-4515-aaa7-dc716ab51167">

Error Handling
1) Error will be thrown if requested account or customer not found.
  <img width="551" alt="image" src="https://github.com/ajitp14/banking/assets/108980419/195de9ad-94ee-4d48-ab73-1ec2306ba997">
   
2) Error will be thrown if cusotmer tried to make transaction of amoun more than his balance.
 <img width="553" alt="image" src="https://github.com/ajitp14/banking/assets/108980419/4216e06d-9ed6-4227-8aba-b71a9462b703">

