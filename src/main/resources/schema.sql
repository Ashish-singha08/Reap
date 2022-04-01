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
                               `Id` BIGINT PRIMARY KEY,
                               `GiverId` BIGINT NOT NULL,
                               `TakerId` BIGINT NOT NULL,
                               `EndorsedOn`  DATETIME NOT NULL,
                               `Message` VARCHAR(300) NOT NULL,
                               `CoinsEndorsed` INT NOT NULL,
                               `TagId` BIGINT NOT NULL
)

CREATE TABLE `Order` (
                               `Id` BIGINT PRIMARY KEY,
                               `UserId` BIGINT NOT NULL,
                               `OrderedOn`  DATETIME NOT NULL,
                               `ItemId` BIGINT NOT NULL
)

ALTER TABLE `Endorsement` ADD CONSTRAINT FK_Endorsement_GiverUser FOREIGN KEY (`GiverId`) REFERENCES `User` (`Id`);

ALTER TABLE `Endorsement` ADD CONSTRAINT FK_Endorsement_TakerUser FOREIGN KEY (`TakerId`) REFERENCES `User` (`Id`);

ALTER TABLE `Endorsement` ADD CONSTRAINT FK_Endorsement_TagUsedByUser FOREIGN KEY (`TagId`) REFERENCES `Tags` (`Id`);

ALTER TABLE `Order` ADD CONSTRAINT FK_Order_User FOREIGN KEY (`ItemId`) REFERENCES `Items` (`Id`);