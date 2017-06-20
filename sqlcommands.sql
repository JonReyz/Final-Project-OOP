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

/*---------------------------------------Inserções no banco de dados --------------------------------------------------------------------------------*/
/*---------------------------------- Arrumar a tabela animais-----------------------------------------------------------------*/


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


/*---------------------------------------Inserções no banco de dados --------------------------------------------------------------------------------*/

insert into Users(login,passwd,type) values('admin','admin',0);
insert into Users(login,passwd,type) values('ong','ong',1);
insert into Users(login,passwd,type) values('doghelp', 'doghelp',1);
insert into Guardians(name,email,login) values('Teste de cliente','teste@cliente.com',(select login from Users where login='admin'));
insert into Ongs(email,adress,phone,cnpj,description,login) values('Ong@email','Rua da ong','32419262','123123123','Uma ong legal',(select login from Users where login='ong'));
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Obitinho','cachorro','M',0,'pequeno','pequeno','dócil','asd@asd',10,0,(select login from Users where login='ong'),'heeey');



insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Obitão','cachorro','M','0','Grande','Grande','Brabo',3,'0',(select login from Users where login='ong'),'Obitão é brabo')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Pillow','gato','F','0','Pequena','Curto','Dócil',3,'0',(select login from Users where login='ong'),'Uma gatinha fofa')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Filé','cachorro','F','0','Grande','Longa','Brincalhona',4,'1',(select login from Users where login='doghelp'),'Vira-lata de Golden')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Rajada','gato','M','0','Pequena','Curto','Saideiro',6,'1',(select login from Users where login='ong'),'Gato amoroso e brincalhao')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Juca','cachorro','M','0','Medio','Curto','Tranquilo',8,'0',(select login from Users where login='doghelp'),'Cão já velhinho, mal dá trabalho')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Doimo Jr.','ave','M','0','Medio','Longa','Companheiro', 2,'0',(select login from Users where login='admin'),'Cacatua abandonada ainda filhote')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Croc','repteis','F','0','Medio','Curto','Reservado',6,'0',(select login from Users where login='ong'),'Iguana companheira para momentos reflexivos')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Raphael','repteis','M','0','Pequeno','Curto','Ativo',3,'0',(select login from Users where login='doghelp'),'Cágado super fofo')

insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Zeca','cachorro','M','0','Medio','Grande','Brabo',4,'1',(select login from Users where login='doghelp'),'Cão protetor e territorial')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Gato','gato','M','0','Pequeno','Longo','Passeadeiro',3,'0',(select login from Users where login='ong'),'Um gato companheiro durante o dia e rueiro durante a noite')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Catioro','cachorro','M','0','Grande','Curto','Bagunceiro',4,'1',(select login from Users where login='doghelp'),'Cão amigável e brincalhão')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Duda','ave','F','0','Pequena','Curto','Cantora',5,'0',(select login from Users where login='doghelp'),'Calopsita cantora')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Jovem','cachorro','M','0','Medio','Curto','Tranquilo',4,'1',(select login from Users where login='doghelp'),'Cão bagunceiro mas muito amoroso')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Golias','cachorro','M','0','Pequeno','Longa','Agressivo', 2,'0',(select login from Users where login='ong'),'Vira-lata de Pinscher com Chihuahua')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Soneca','cachorro','F','0','Pequeno','Curto','Carente',4,'0',(select login from Users where login='ong'),'Basset Hound com cara de sono')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,idade,vacinacao,responsavel,description) values('Tortuga','repteis','M','0','Grande','Curto','Solitário',10,'0',(select login from Users where login='admin'),'Jabuti de porte grande')

















