create table community(community_id SMALLINT UNSIGNED not null AUTO_INCREMENT, community_name varchar(100) not null,
community_type ENUM('APARTMENTS','CONDOS','TOWNHOUSES','SINGLE_FAMILY') not null,
active_indicator ENUM('Y','N') not null,community_manager_id SMALLINT UNSIGNED, city CHAR(255) not null,
state CHAR(2) not null, zip CHAR(5) not null, zip4 CHAR(4) not null,
created_timestamp TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP, updated_timestamp TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, created_by Varchar(255) not null,
updated_by Varchar(255), PRIMARY KEY(
community_id));