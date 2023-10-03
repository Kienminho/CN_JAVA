# CN_JAVA
# Hướng dẫn chạy Lab02
Step 1: Run the command "git clone https://github.com/Kienminho/CN_JAVA.git", on any of your folders

Step 2: Move to CN_JAVA/Lab02, run the command "mvn clean package" to create file Program.jar

Step 3: Then, move to /target, run the command 
```bash
java -jar Program.jar "your connect string"
```

Example: java -jar Program.jar "jdbc:mysql://localhost:3306/productmanagement?user=root&password="


# Hướng dẫn chạy Lab023
Step1: Find to main/resources/hibernate.cfg.xml
Step2: Edit: username, password, database name
```
<property name="connection.url">jdbc:mysql://localhost:3306/phone_management?serverTimezone=UTC&amp;useUnicode=true&amp;characterEncoding=utf8</property>
        <property name="connection.username">root</property>
        <property name="connection.password"></property>
```
Step3: Run the program, you will see a menu. Here, you can test CURD against the database.

