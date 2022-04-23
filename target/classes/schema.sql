
USE Reap;

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
                               `Id` INT PRIMARY KEY,
                               `Tag` VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE `Item` (
                        `Id` INT PRIMARY KEY,
                        `Name` VARCHAR(100) NOT NULL UNIQUE,
                        `CoinsRequired` INT NOT NULL
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


INSERT INTO User (Id,Username,FullName,RoleTypeId,Email,PhoneNumber,Password,CoinBalance) VALUES ('3','bs2305','Aman Arora','MT2020012','aman@gmial.com','1234567890','1234','200');