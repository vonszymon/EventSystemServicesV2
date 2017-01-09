create table Event(
	event_id integer primary key autoincrement,
	title varchar(50),
	start_date date,
	content varchar(1024)
);

create table Commentary(
	commentary_id integer primary key autoincrement,
	event_id integer,author varchar(50),
	last_name varchar(50),
	publish_date date,
	content varchar(100),
	foreign key(event_id) references Event(event_id)
);

create table User(
	username varchar(50),
	password varchar(50),
	enabled tinyint,
	role varchar(50),
	primary key(username)
);

insert into User(username, password, enabled, role) values('Admin', '123456', 1, 'ROLE_ADMIN');
insert into User(username, password, enabled, role) values('a', 'a', 1, 'ROLE_ADMIN');
insert into User(username, password, enabled, role) values('User', '123456', 1, 'ROLE_USER');

insert into Event(title,start_date, content) values( 'First event', '2015-01-01', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.');

insert into Event(title,start_date, content) values( 'Second event', '2015-02-01', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.');

insert into Event(title,start_date, content) values( 'Third event', '2015-02-01', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.');

insert into Event(title,start_date, content) values( 'Fourth event', '2015-02-01', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');


insert into Commentary(event_id, author, publish_date, content) values( 1, 'Ala', '2015-01-02', 'I`m first to comment first event.');
insert into Commentary(event_id, author, publish_date, content) values( 1, 'Ola', '2015-01-02', 'I`m second to comment first event.');
insert into Commentary(event_id, author, publish_date, content) values( 1, 'Ewa', '2015-01-02', 'I`m third to comment first event.');
insert into Commentary(event_id, author, publish_date, content) values( 2, 'Ala', '2015-01-02', 'I`m first to comment second event.');
insert into Commentary(event_id, author, publish_date, content) values( 2, 'Ewa', '2015-01-02', 'I`m second to comment second event.');
insert into Commentary(event_id, author, publish_date, content) values( 4, 'Ewa', '2015-01-02', 'I`m first to comment fourth event.');
