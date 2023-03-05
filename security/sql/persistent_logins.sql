CREATE TABLE `persistent_logins` (
                                     `username` varchar(64) NOT NULL,
                                     `series` varchar(64) PRIMARY KEY,
                                     `token` varchar(64) NOT NULL,
                                     `last_used` timestamp NOT NULL
);
