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



insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Obitao','cachorro','M','0','Grande','Grande','Brabo','email',3,'0','roddala','Obitão é brabo', 'obitao.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Pillow','gato','F','0','Pequena','Curto','Dócil','email',3,'0','dan','Uma gatinha fofa', 'pillow.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('File','cachorro','F','0','Grande','Longa','Brinca','email',4,'1','jon','Vira-lata de Golden', 'file.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Rajada','gato','M','0','Pequena','Curto','Saideiro','email',6,'1','doimo','Gato amoroso e brincalhao', 'rajada.jpeg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Juca','cachorro','M','0','Medio','Curto','Tranquilo','email',8,'0','roddala','Cão já velhinho, mal dá trabalho', 'juca.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Doimo Jr.','ave','M','0','Medio','Longa','Companha', 'email',2,'0','dan','Cacatua abandonada ainda filhote', 'jr.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Croc','repteis','F','0','Medio','Curto','Reservado','email',6,'0','jon','Iguana companheira para momentos reflexivos', 'croc.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Raphael','repteis','M','0','Pequeno','Curto','Ativo','email',3,'0','doimo','Cágado super fofo', 'raphael.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Zeca','cachorro','M','0','Medio','Grande','Brabo','email',4,'1','roddala','Cão protetor e territorial', 'zeca.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Gato','gato','M','0','Pequeno','Longo','Passea','email',3,'0','dan','Um gato companheiro durante o dia e rueiro durante a noite', 'gato.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Catioro','cachorro','M','0','Grande','Curto','Bagunca','email',4,'1','jon','Cão amigável e brincalhão', 'catioro.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Duda','ave','F','0','Pequena','Curto','Cantora','email',5,'0','doimo','Calopsita cantora', 'duda.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Jovem','cachorro','M','0','Medio','Curto','Tranquilo','email',4,'1','roddala','Cão bagunceiro mas muito amoroso', 'jovem.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Golias','cachorro','M','0','Pequeno','Longa','Agressivo', 'email',2,'0','dan','Vira-lata de Pinscher com Chihuahua', 'golias.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Soneca','cachorro','F','0','Pequeno','Curto','Carente','email',4,'0','jon','Basset Hound com cara de sono', 'soneca.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Tortuga','repteis','M','0','Grande','Curto','Solitário','email',10,'0','doimo','Jabuti de porte grande', 'tortuga.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Pirulito','aves','M','0','Medio','Penas','Agitado','email',2,'0','roddala','Lóris filhote. Muito dócil', 'pirulito.jpg')
insert into Animals(name,tipo,sexo,status,porte,pelagem, temperamento,email,idade,vacinacao,responsavel,description, foto) values('Agulha','repteis','F','0','Grande','Couro','Calma','email',1,'0','dan','Cobra Phyton amarela super mansa', 'agulha.jpg')

















