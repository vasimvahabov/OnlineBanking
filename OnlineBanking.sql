create database online_banking;
use online_banking;

create table _users(
  _user_id int primary key auto_increment,
  _first_name varchar(50) not null,
  _last_name varchar(50) not null,
  _email varchar(255) not null unique,
  _password varchar(255) not null,
  _token varchar(255),
  _code int,
  _verified int default 0,
  _verified_at datetime,
  _created_at timestamp default now(),
  _updated_at timestamp default now()
);

create table _accounts(
  _account_id int primary key auto_increment,
  _user_id int not null,
  _account_number varchar(100) not null,
  _account_name varchar(50) not null,
  _account_type varchar(50) not null,
  _balance decimal(18,2) default 0.00,
  _created_at timestamp default now(),
  _updated_at timestamp default now(),
  foreign key(_user_id) references _users(_user_id) on delete cascade
);

create table _transactions(
  _transaction_id int primary key auto_increment,
  _account_id int not null,
  _transaction_type varchar(50) not null,
  _amount decimal(18,2),
  _source varchar(50),
  _status varchar(50),
  _reason_code varchar(100),
  _created_at timestamp default now(),
  foreign key(_account_id) references _accounts(_account_id) on delete cascade
);

create table _payments(
  _payment_id int primary key auto_increment,
  _account_id int,
  _beneficiary varchar(50),
  _beneficiary_acc_no varchar(255),
  _amount decimal(18,2),
  _reference_no varchar(100),
  _status varchar(50),
  _reason_code varchar(100),
  _created_at timestamp default now(),
  foreign key(_account_id) references _accounts(_account_id) on delete cascade
);

create view _v_transactions
as select 
  t._transaction_id,
  a._account_id,
  u._user_id,
  t._transaction_type,
  t._amount,
  t._source,
  t._status,
  t._reason_code,
  t._created_at
from _transactions as t inner join _accounts as a
  on t._account_id=a._account_id
    inner join _users as u 
      on a._user_id=u._user_id;
      
create view _v_payments   
as select 
  p._payment_id,
  a._account_id,
  u._user_id,
  p._beneficiary,
  p._beneficiary_acc_no,
  p._amount,
  p._reference_no,
  p._status,
  p._reason_code,
  p._created_at
from _payments as p inner join _accounts as a
  on p._account_id=a._account_id
    inner join _users as u
      on a._user_id=u._user_id; 