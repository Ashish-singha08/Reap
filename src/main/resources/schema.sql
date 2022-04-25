
USE Reap;
# DROP TABLE `User`;
# DROP TABLE `Tag`;
# DROP TABLE `Item`;
# DROP TABLE `Questions`;
# DROP TABLE `Endorsement`;
# DROP TABLE `Notification`;
# DROP TABLE `Orders`;
CREATE TABLE `User` (
                        `Id` BIGINT PRIMARY KEY,
                        `Username` VARCHAR(100) NOT NULL UNIQUE,
                        `FullName` VARCHAR(100) NOT NULL,
                        `RoleTypeId` VARCHAR(100) NOT NULL,
                        `Email` VARCHAR(254) NOT NULL UNIQUE,
                        `PhoneNumber` VARCHAR(100) NOT NULL UNIQUE,
                        `Password` VARCHAR(100) NOT NULL,
                        `CoinBalance` INT NOT NULL
);

CREATE TABLE `Tag` (
                               `Id` BIGINT PRIMARY KEY,
                               `Tag` VARCHAR(100) NOT NULL UNIQUE,
                                `imageUrl` VARCHAR(200) NOT NULL UNIQUE
);

CREATE TABLE `Item` (
                        `Id` BIGINT PRIMARY KEY,
                        `Name` VARCHAR(100) NOT NULL UNIQUE,
                        `CoinsRequired` INT NOT NULL,
                        `imageUrl` VARCHAR(200) NOT NULL UNIQUE
);

CREATE TABLE `Endorsement` (
                               `Id` BIGINT PRIMARY KEY AUTO_INCREMENT,
                               `GiverId` BIGINT NOT NULL,
                               `TakerId` BIGINT NOT NULL,
                               `EndorsedOn`  DATETIME NOT NULL,
                               `Message` VARCHAR(300) NOT NULL,
                               `CoinsEndorsed` INT NOT NULL,
                               `TagId` BIGINT NOT NULL
);

CREATE TABLE `Orders` (
                               `Id` BIGINT PRIMARY KEY AUTO_INCREMENT,
                               `UserId` BIGINT NOT NULL,
                               `OrderedOn`  DATETIME NOT NULL,
                               `ItemId` BIGINT NOT NULL
);
CREATE TABLE Notification(
                                `Id` BIGINT PRIMARY KEY AUTO_INCREMENT,
                                `NotificationTo` BIGINT NOT NULL,
                                `NotificationFrom`BIGINT NOT NULL,
                                `Message` VARCHAR (200) NOT NULL,
                                `CreatedOn`  DATETIME NOT NULL,
                                `isVisited` INT NOT NULL,
                                `isForEndorsement` INT NOT NULL
);

CREATE TABLE Questions(
                                `Id` BIGINT PRIMARY KEY AUTO_INCREMENT,
                                `Question` VARCHAR(300) NOT NULL ,
                                `AskedByUserId` BIGINT NOT NULL,
                                `AskedToUserId` BIGINT NOT NULL,
                                `AnsweredByUserId` BIGINT,
                                `ForwardedByUserId` BIGINT,
                                 `AskedOn` TIMESTAMP,
                                `Answer` VARCHAR(400)
);
ALTER TABLE `Endorsement` ADD CONSTRAINT FK_Endorsement_GiverUser FOREIGN KEY (`GiverId`) REFERENCES `User` (`Id`);

ALTER TABLE `Endorsement` ADD CONSTRAINT FK_Endorsement_TakerUser FOREIGN KEY (`TakerId`) REFERENCES `User` (`Id`);

ALTER TABLE `Endorsement` ADD CONSTRAINT FK_Endorsement_TagUsedByUser FOREIGN KEY (`TagId`) REFERENCES `Tag` (`Id`);

ALTER TABLE `Orders` ADD CONSTRAINT FK_Order_User FOREIGN KEY (`ItemId`) REFERENCES `Item` (`Id`);

ALTER TABLE `Notification` ADD CONSTRAINT FK_NotificationTo_Userid FOREIGN KEY (`NotificationTo`) REFERENCES `User` (`Id`);

ALTER TABLE `Notification` ADD CONSTRAINT FK_NotificationFrom_Userid FOREIGN KEY (`NotificationFrom`) REFERENCES `User` (`Id`);

ALTER TABLE `Questions` ADD CONSTRAINT Fk_Question_UserId FOREIGN KEY (`AskedByUserId`) REFERENCES `User` (`Id`);

ALTER TABLE `Questions` ADD CONSTRAINT Fk_QuestionTo_UserId FOREIGN KEY (`AskedToUserId`) REFERENCES `User` (`Id`);

ALTER TABLE `Questions` ADD CONSTRAINT Fk_QuestionAnswered_UserId FOREIGN KEY (`AnsweredByUserId`) REFERENCES `User` (`Id`);

ALTER TABLE `Questions` ADD CONSTRAINT Fk_QuestionForwardedBy_UserId FOREIGN KEY (`ForwardedByUserId`) REFERENCES `User` (`Id`);


INSERT INTO User (Id,Username,FullName,RoleTypeId,Email,PhoneNumber,Password,CoinBalance) VALUES ('1','bs2305','Aman Arora','MT2020012','aman@gmial.com','1234567890','1234','200');

INSERT INTO reap.item (Id, Name, CoinsRequired, imageUrl) VALUES (1, 'Subway 50 % Coupon', 40, 'https://github.com/Ashish-singha08/BudgetApp/blob/master/images/Brown%20and%20Pastel%20Red%20Cafe%20Drink%20Ilustrated%20Thank%20You%20Card.jpg?raw=true');

INSERT INTO reap.item (Id, Name, CoinsRequired, imageUrl) VALUES (2, 'Pet Shop Coupon Card', 50, 'https://github.com/Ashish-singha08/BudgetApp/blob/master/images/Yellow%20Minimalist%20Pet%20Shop%20(Card%20(Portrait)).jpg?raw=true');

INSERT INTO reap.item (Id, Name, CoinsRequired, imageUrl) VALUES (3, 'Free Entry in Arena', 100, 'https://github.com/Ashish-singha08/BudgetApp/blob/master/images/Modern%20You''re%20the%20Wine%20I%20Want%20Card%20.jpg?raw=true');

INSERT INTO reap.item (Id, Name, CoinsRequired, imageUrl) VALUES (4, 'Amazon Gift Card', 50, 'https://github.com/Ashish-singha08/BudgetApp/blob/master/images/Cartoon%20White%20Pink%20Cute%20Bear%20with%20a%20Heart%20Greeting%20Card.jpg?raw=true');

INSERT INTO reap.tag (Id, Tag, imageUrl) VALUES (1, 'THE BOSS', 'https://github.com/Ashish-singha08/BudgetApp/blob/master/tags/boss.jpg?raw=true');

INSERT INTO reap.tag (Id, Tag, imageUrl) VALUES (2, 'FABULOUS', 'https://github.com/Ashish-singha08/BudgetApp/blob/master/tags/fab.jpg?raw=true');

INSERT INTO reap.tag (Id, Tag, imageUrl) VALUES (3, 'WINNER', 'https://github.com/Ashish-singha08/BudgetApp/blob/master/tags/winner.jpg?raw=true');

INSERT INTO reap.tag (Id, Tag, imageUrl) VALUES (4, 'BELIEVER', 'https://github.com/Ashish-singha08/BudgetApp/blob/master/tags/beliver.jpg?raw=true');

INSERT INTO reap.tag (Id, Tag, imageUrl) VALUES (5, 'GAME CHANGER', 'https://github.com/Ashish-singha08/BudgetApp/blob/master/tags/game.jpg?raw=true');

INSERT INTO reap.tag (Id, Tag, imageUrl) VALUES (6, 'THE ARMOR', 'https://github.com/Ashish-singha08/BudgetApp/blob/master/tags/armor.jpg?raw=true');
INSERT INTO reap.tag (Id, Tag, imageUrl) VALUES (7, 'OPTIMIZED BRAIN', 'https://github.com/Ashish-singha08/BudgetApp/blob/master/tags/brain.jpg?raw=true');

INSERT INTO reap.tag (Id, Tag, imageUrl) VALUES (8, 'EXTRA MILER', 'https://github.com/Ashish-singha08/BudgetApp/blob/master/tags/extra.jpg?raw=true');

