/*
---------------------------------- Criações das tabelas (Qualquer alteração dar drop em todas as tabelas ou apagar o banco inteiro :/ ) ---------------------------------------
*/

create table Users (
login varchar(32) not null, 
passwd varchar(32) not null, 
type int, 
PRIMARY_KEY (login),
);

/* Tem uma chave estrangeira da tabela users*/
create table Guardians (
guardian_id int not null auto_increment as identity constraint guardian_pk primary key,
name varchar(32) not null,
email varchar(32) not null,
login varchar(32) not null,
foreign key (login) references Users(login)  
);

/* Tem uma chave estrangeira da tabela users*/
create table Animals (
animal_id int not null auto_increment as identity constraint animal_pk primary key,
name varchar(32) not null,
tipo varchar(10) not null,
sexo char not null,
status binary not null,
porte varchar(10) not null,
pelagem varchar(10) not null,
temperamento varchar(10) not null,
email varchar(32) not null,
idade int not null,
vacinacao binary not null,
responsavel int not null,
description varchar(100) not null,
foreign key (responsavel) references Users(login) 
);
	
/* Tem uma chave estrangeira da tabela users*/
create table Ongs (
ong_id int not null auto_increment as identity constraint ong_pk primary key,
email varchar(32) not null,
adress varchar(32) not null,
phone varchar(20) not null,
cnpj varchar(20) not null,
description varchar(100) not null,
foreign key (login) references Users(login)  
);


create table Adoptions (
adp_id int not null auto_increment as identity constraint adp_pk primary key,
user varchar(32) not null,
animal int not null,
foreign key (user) references Users(login),
foreign key (animal) references Animals(animal_id),
);

"create table Users ("
+ "login varchar(32) not null constraint user_pk primary key , "
+ "passwd varchar(32) not null," 
+ "type int" 
+ ")"

"create table Guardians ("
+"guardian_id int not null generated always as identity (start with 0, increment by 1),"
+"name varchar(32) not null,"
+"email varchar(32) not null,"
+"login varchar(32) not null,"
+"CONSTRAINT guardians_pk PRIMARY KEY (guardian_id),"
+"CONSTRAINT guardians_fk FOREIGN KEY (login) REFERENCES Users(login)"
+")";



"create table Animals ("
+"animal_id int not null generated always as identity (start with 0, increment by 1),"
+"name varchar(32) not null,"
+"tipo varchar(10) not null,"
+"sexo char not null,"
+"status char not null,"
+"porte varchar(10) not null,"
+"pelagem varchar(10) not null,"
+"temperamento varchar(10) not null,"
+"email varchar(32) not null,"
+"idade int not null,"
+"vacinacao char not null,"
+"responsavel varchar(32) not null,"
+"description varchar(100) not null,"
+"CONSTRAINT animals_pk PRIMARY KEY (animal_id),"
+"CONSTRAINT animals_fk FOREIGN KEY (responsavel) REFERENCES Users(login)"
+")"; 
"create table Ongs ("
+"ong_id int not null generated always as identity (start with 0, increment by 1),"
+"email varchar(32) not null,"
+"adress varchar(32) not null,"
+"phone varchar(20) not null,"
+"cnpj varchar(20) not null,"
+"description varchar(100) not null,"
+"login varchar(32) not null,"
+"CONSTRAINT ong_pk PRIMARY KEY (ong_id),"
+"CONSTRAINT ong_fk FOREIGN KEY (login) REFERENCES Users(login)"
+")";

"create table Adoptions ("
+"adp_id int not null generated always as identity (start with 0, increment by 1),"
+"guardian varchar(32) not null,"
+"animal int not null,"
+"CONSTRAINT adp_pk PRIMARY KEY (adp_id),"
+"CONSTRAINT adpU_fk FOREIGN KEY (guardian) REFERENCES Users(login),"
+"CONSTRAINT adpA_fk FOREIGN KEY (animal) REFERENCES Animals(animal_id)"
+")";