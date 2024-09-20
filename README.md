Project Name :- EmployeAndTaxCalculation

DataBase : - Mysql IDE :- STS

Clone the Repository :- git clone https://github.com/Devahanuk77/imaginnovative_employeeTax_Project.git cd your-repository name
Configure Database :- Mysql setup:-

1.create A database :- query :- CREATE DATABASE employee_tax; [employee_tax is the database name].

add below details in the application.properties file:- spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name spring.datasource.username=your_username spring.datasource.password=your_password

Build the project :- mvn clean install by using this command.
Run the application by using springboot starter.

For Documentation i implemented open swagger UI.
2. Open API url :- http://localhost:8080/swagger-ui/index.html
