use master

CREATE DATABASE QuizOnline

USE QuizOnline

DROP TABLE Questions

DROP TABLE Account

CREATE SEQUENCE AccountSeq START WITH 1 INCREMENT BY 1;

CREATE TABLE Account (
  account_id VARCHAR(50) PRIMARY KEY,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  fullname VARCHAR(50) ,
  phone int NOT NULL,
  status bit NOT NULL
);
CREATE TABLE AccRole (
	roleID INT IDENTITY(1,1) NOT NULL,
	account_id VARCHAR(50) NOT NULL,
	RoleCode VARCHAR(50) NOT NULL,
	FOREIGN KEY (account_id) REFERENCES Account(account_id),
	FOREIGN KEY (RoleCode) REFERENCES Role(RoleCode)
);
CREATE TABLE Role (
	RoleCode VARCHAR(50) NOT NULL PRIMARY KEY,
	RoleName VARCHAR(50) NOT NULL,
	Permission VARCHAR(50) NOT NULL
);

DROP TABLE Subjects
CREATE TABLE Subjects (
  subjectID VARCHAR(50) NOT NULL,
  subjectName VARCHAR(255) NOT NULL,
  numberQuestion INT NOT NULL,
  timeQuiz INT NOT NULL,
  status BIT NOT NULL,
  PRIMARY KEY (subjectID)
);

DROP TABLE Questions

CREATE SEQUENCE QuestionSeq START WITH 1 INCREMENT BY 1;
CREATE TABLE Questions (
  questionID VARCHAR(50) NOT NULL PRIMARY KEY,
  question_content VARCHAR(255) NOT NULL,
  createDate DATE NOT NULL,
  subjectID VARCHAR(50) NOT NULL,
  status BIT NOT NULL,
  FOREIGN KEY (subjectID) REFERENCES Subjects(subjectID)
);

DROP TABLE Answers
CREATE TABLE Answers (
  answerID INT IDENTITY(1,1),
  answer_content VARCHAR(255) NOT NULL,
  answer_correct BIT NOT NULL,
  questionID VARCHAR(50) NOT NULL,
  status BIT NOT NULL,
  PRIMARY KEY (answerID),
  FOREIGN KEY (questionID) REFERENCES Questions(questionID)
);

DROP TABLE Quizs
 CREATE TABLE Quizs(
	[quizID] VARCHAR(50),
	[account_id] VARCHAR(50) NULL,
	[subjectID] varchar(50) NULL,
	[totalMark] FLOAT NULL,
	[finishedQuizTime] [datetime] NULL,
	[status] BIT NULL,
	FOREIGN KEY (account_id) REFERENCES Account(account_id),
	FOREIGN KEY (subjectID) REFERENCES Subjects(subjectID),
	PRIMARY KEY (quizID)
);

DROP TABLE QuizDetail
CREATE TABLE QuizDetail(
	[detailID] int IDENTITY (1,1) NOT NULL,
	[quizID] VARCHAR(50),
	[questionID] varchar(50) NULL,
	[selectedAnswer] int NULL,
	[result] BIT NULL,
	[status] BIT NULL,
	FOREIGN KEY (questionID) REFERENCES Questions(questionID),
	FOREIGN KEY (quizID) REFERENCES Quizs(quizID)
 );

DELETE Account WHERE email = 'admin@gmail.com'

INSERT [dbo].[Role] ([RoleCode],[RoleName],[Permission]) VALUES ('1','Admin','Read,Write,Exec')
INSERT [dbo].[Role] ([RoleCode],[RoleName],[Permission]) VALUES ('2','User','Read,Write')

INSERT [dbo].[AccRole] ([account_id],[RoleCode]) VALUES ('acc_0001','1')
INSERT [dbo].[AccRole] ([account_id],[RoleCode]) VALUES ('acc_0002','2')
SELECT NEXT VALUE FOR AccountSeq AS AccountNum
INSERT INTO Account(account_id, email, password, fullname, phone, status) VALUES ('acc_0001', 'admin@gmail.com','123','Lam Nhat Tien','123456789','1');
INSERT INTO Account(account_id, email, password, fullname, phone, status) VALUES ('acc_0002', 'test1@gmail.com','123','Test User','123456789','1');

SET IDENTITY_INSERT [dbo].[Answers] ON
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (1, N'A status of 200 to 299 signifies that the request was successful. d', N'QE1', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (2, N'A status of 300 to 399 is informational messages.', N'QE1', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (3, N'A status of 400 to 499 indicates an error in the server', N'QE1', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (4, N'A status of 500 to 599 indicates an error in the client.', N'QE1', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (5, N'Http', N'QE2', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (6, N'Servlet', N'QE2', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (7, N'HttpServletRequest', N'QE2', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (8, N'HttpServletResponse', N'QE2', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (9, N'response.setContentType("image/gif"); d', N'QE3', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (10, N'response.setType("application/gif");', N'QE3', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (11, N'response.setContentType("application/bin");', N'QE3', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (12, N'response.setType("image/gif");', N'QE3', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (13, N'ReportServlet will throw exception at runtime.', N'QE4', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (14, N'ReportServlet.java wont compile.', N'QE4', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (15, N'ReportServlet.java will compile  d', N'QE4', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (16, N'All', N'QE4', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (17, N'doGet(HttpServletRequest, HttpServletResponse); d', N'QE5', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (18, N'doPost(HttpServletRequest, HttpServletResponse);', N'QE5', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (19, N'doHead(HttpServletRequest, HttpServletResponse);', N'QE5', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (20, N'doOption(HttpServletRequest, HttpServletResponse);', N'QE5', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (21, N'This will only work for HTTP GET requests', N'QE6', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (22, N'This will only work for HTTP POST requests', N'QE6', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (23, N' This will work for HTTP GET as well as POST requests. d', N'QE6', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (24, N'It ll throw an exception at runtime only if a POST request is sent to it.', N'QE6', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (25, N'Information resources(sources) on the Internet d', N'QE7', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (26, N'E-mail addresses for use in the Internet', N'QE7', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (27, N'IP addresses of servers connected to the Internet', N'QE7', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (28, N'Owners of PCs connected to the Internet', N'QE7', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (29, N'It is an XML document.', N'QE8', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (30, N'It cannot be unpackaged by the container.', N'QE8', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (31, N'It is used by web application developer to deliver ', N'QE8', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (32, N'It contains web components  d', N'QE8', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (33, N'It is accessible to all the servlets of the webapp. d', N'QE9', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (34, N'It is accessible to all the servlets of all the webapps ', N'QE9', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (35, N'It is accessible only to the servlet it is defined for.', N'QE9', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (36, N'All', N'QE9', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (37, N'Web Container d', N'QE10', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (38, N'EJB Container', N'QE10', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (39, N'Servlets', N'QE10', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (40, N'Applets', N'QE10', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (41, N'Cookie d', N'QE11', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (42, N'Session', N'QE11', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (43, N'Request', N'QE11', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (44, N'Response', N'QE11', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (45, N'getObject("roleName");', N'QE12', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (46, N'getValue("roleName"); d', N'QE12', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (47, N'get("roleName");', N'QE12', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (48, N'getAttribute("roleName");', N'QE12', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (49, N'RecordSet', N'QE13', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (50, N'ResultSet d', N'QE13', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (51, N'DataSet', N'QE13', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (52, N'RowSet', N'QE13', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (53, N'Single -tier and two tier', N'QE14', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (54, N'None of the others', N'QE14', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (55, N'Three-tier and four tier', N'QE14', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (56, N'two-tier and three-tier', N'QE14', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (57, N'thread inherit their priority from their parent thread', N'QE15', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (58, N'Thread are guanteed to run with the priority d', N'QE15', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (59, N'Every thread starts executing with a priority of 5', N'QE15', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (60, N'Thread priority is an integer ranging from 1 to 100', N'QE15', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (61, N'java.awt.BorderLayout', N'QE16', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (62, N'java.awt.GridLayout d', N'QE16', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (63, N'java.awt.CardLayout', N'QE16', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (64, N'java.awt.FlowLayout', N'QE16', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (65, N'ODBCDriver', N'QE17', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (66, N'Driver', N'QE17', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (67, N'none of the others', N'QE17', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (68, N'DriverManager d', N'QE17', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (69, N'JScrollPane', N'QE18', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (70, N'JFrame', N'QE18', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (71, N'JLabel d', N'QE18', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (72, N'JPanel', N'QE18', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (73, N'The programer can specify which thread should be notified', N'QE19', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (74, N'the wait() and notify() methods can be called outside synchronized code', N'QE19', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (75, N'The thread that call wait() goes into the monitors pool d', N'QE19', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (76, N'The thread that calls notify() gives up the lock', N'QE19', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (77, N'java.awt.Transform', N'QE20', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (78, N'javax.swing.Component', N'QE20', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (79, N' java.awt. Two Dimensional Graphics', N'QE20', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (80, N'java.awt. Two Dimensional Graphics 2D  d', N'QE20', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (81, N'java.awt.FontMetric', N'QE21', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (82, N'java.util.FontMetric', N'QE21', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (83, N'javax.swing.FontMetric', N'QE21', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (84, N'java.awt.FontMetrics d', N'QE21', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (85, N'Vary the priorities of your threads', N'QE22', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (86, N'Synchronize access to all shared variables', N'QE22', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (87, N'there is no single technique that can guarantee non-deadlocking code d', N'QE22', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (88, N'Make sure all threads yield from time to time', N'QE22', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (89, N'GirdLayout', N'QE23', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (90, N'FlowLayout d', N'QE23', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (91, N'GirdBagLayout', N'QE23', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (92, N'BorderLayout', N'QE23', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (93, N'JListBox', N'QE24', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (94, N'JTextField', N'QE24', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (95, N'JLabel d', N'QE24', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (96, N'JButton', N'QE24', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (97, N'char c = 0x1234;', N'QE25', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (98, N'char c = \u1234;', N'QE25', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (99, N'char c = "\u1234";', N'QE25', 0, 1)
GO
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (100, N'None d', N'QE25', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (101, N'int x = -1; x = x >>> 5; d', N'QE26', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (102, N'int x = -1; x = x >>> 32;', N'QE26', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (103, N'byte x = -1; x = x >>> 5;', N'QE26', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (104, N'int x = -1; x = x >> 5;', N'QE26', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (105, N'System.free();', N'QE27', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (106, N'System.setGarbageCollection();', N'QE27', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (107, N'System.gc(); d', N'QE27', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (108, N'System.out.gc();', N'QE27', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (109, N'Comparable d', N'QE28', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (110, N'Sortator', N'QE28', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (111, N'Sortable', N'QE28', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (112, N'Comparator', N'QE28', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (113, N'FileOutputStream- ObjectOutputStream d', N'QE29', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (114, N'FileReader - ObjectOutputStream', N'QE29', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (115, N'File - ObjectOutputStream - FileOutputStream', N'QE29', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (116, N'File - ObjectOutputStream - Writer', N'QE29', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (117, N'transient', N'QE30', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (118, N'final d', N'QE30', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (119, N'private', N'QE30', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (120, N'static', N'QE30', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (121, N'String objects are constants. StringBuffer objects are not d', N'QE31', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (122, N'StringBuffer objects are constants. String objects are not', N'QE31', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (123, N'Both String and StringBuffer pbject are constants', N'QE31', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (124, N'Both String and StringBuffer pbject are not constants', N'QE31', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (125, N'Inheritance', N'QE32', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (126, N'Overloading', N'QE32', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (127, N'Polymorphism d', N'QE32', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (128, N'Astraction', N'QE32', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (129, N'List <String> theVec = new Vector<String>();', N'QE33', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (130, N'List<String> theList = new Vector<String>(); d', N'QE33', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (131, N'List<String> theList = new Vector<String>;', N'QE33', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (132, N'Vector<String> theVec = new Vector<String>;', N'QE33', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (133, N'makeNewFile()', N'QE34', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (134, N'newFile()', N'QE34', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (135, N'makerFile()', N'QE34', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (136, N'createNewFile() d', N'QE34', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (137, N'StringBuider [] contents = newFile.list();', N'QE35', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (138, N'The File class does not provide a way to list ', N'QE35', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (139, N'String [] contents = myFile.list(); d', N'QE35', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (140, N'File [] contents = myFile.list();', N'QE35', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (141, N'attribute d', N'QE36', 1, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (142, N'Method', N'QE36', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (143, N'Message', N'QE36', 0, 1)
INSERT [dbo].[Answers] ([answerID], [answer_content], [questionID], [answer_correct], [status]) VALUES (144, N'Operation', N'QE36', 0, 1)
SET IDENTITY_INSERT [dbo].[Answers] OFF
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE1', N'Which of the following statements are correct about the status of the Http response?', CAST(N'2021-02-01' AS Date), N'PRJ321', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE10', N'A ... manages the threading for the servlets and JSPs ', CAST(N'2021-02-01' AS Date), N'PRJ321', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE11', N'A __has a name, a single value, and optional attributes', CAST(N'2021-02-01' AS Date), N'PRJ321', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE12', N'Which of the following method calls can be used to retrieve an object', CAST(N'2021-02-01' AS Date), N'PRJ321', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE13', N'Statement object return SQL query result as ____ objects', CAST(N'2021-02-01' AS Date), N'PRJ311', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE14', N'JDBC supports ____ and____ models', CAST(N'2021-02-01' AS Date), N'PRJ311', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE15', N'which of the following statements about threads is true?', CAST(N'2021-02-01' AS Date), N'PRJ311', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE16', N'which of the following layout manager will present components with the same size', CAST(N'2021-02-01' AS Date), N'PRJ311', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE17', N'the ____ class is the primary class that has the driver information', CAST(N'2021-02-01' AS Date), N'PRJ311', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE18', N'Which of the following components is not a container type?', CAST(N'2021-02-01' AS Date), N'PRJ311', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE19', N'Which of the following statements about the wait() and notify()', CAST(N'2021-02-01' AS Date), N'PRJ311', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE2', N'Classes HttpServlet and GenericServlet implement the ___ interface.', CAST(N'2021-02-01' AS Date), N'PRJ321', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE20', N'The class ___ contains methods for drawing coordinated', CAST(N'2021-02-01' AS Date), N'PRJ311', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE21', N'to get metric data of a font, the class ___ should be used', CAST(N'2021-02-01' AS Date), N'PRJ311', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE22', N'How can you ensure that multithreaded code does not deadlock', CAST(N'2021-02-01' AS Date), N'PRJ311', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE23', N'The default layout manager of every JPanel', CAST(N'2021-02-01' AS Date), N'PRJ311', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE24', N'which component can display an image, but cannot get focus?', CAST(N'2021-02-01' AS Date), N'PRJ311', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE25', N'Which of the following are legal?', CAST(N'2021-02-01' AS Date), N'PRO192', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE26', N'Which of the following expressions results in a positive value in x?', CAST(N'2021-02-01' AS Date), N'PRO192', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE27', N'which of the following is the correct syntax for suggesting that the JVM', CAST(N'2021-02-01' AS Date), N'PRO192', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE28', N'when creating your own class and you want to make it directly support sorting,', CAST(N'2021-02-01' AS Date), N'PRO192', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE29', N'To write object to an object file. The right order of object creations is:', CAST(N'2021-02-01' AS Date), N'PRO192', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE3', N'You have to send a gif image to the client as a response to a request.', CAST(N'2021-02-01' AS Date), N'PRJ321', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE30', N'which of the following modifiers does not allow a variable to be modified it was initialized?', CAST(N'2021-02-01' AS Date), N'PRO192', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE31', N'Select correct statement', CAST(N'2021-02-01' AS Date), N'PRO192', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE32', N'the ability of a programming language to process objects differently depending on their type is', CAST(N'2021-02-01' AS Date), N'PRO192', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE33', N'which of the following are legal?', CAST(N'2021-02-01' AS Date), N'PRO192', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE34', N'what method of the java.io.File class can create a file on the hard drive?', CAST(N'2021-02-01' AS Date), N'PRO192', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE35', N'how do you use the File class to list the contents of a directory?', CAST(N'2021-02-01' AS Date), N'PRO192', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE36', N'a(n) ____ is a characteristic that describes an object', CAST(N'2021-02-01' AS Date), N'PRO192', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE4', N'Consider the code of ReportServlet servlet of a web application. Assuming generateReport() is valid method and have no problems?', CAST(N'2021-02-01' AS Date), N'PRJ321', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE5', N'Which method of ReportGeneratorServlet will be called when the user clicks on the URL shown by the following HTML.?', CAST(N'2021-02-01' AS Date), N'PRJ321', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE6', N' doGet() and doPost() methods of TestServlet.', CAST(N'2021-02-01' AS Date), N'PRJ321', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE7', N'Which of the following is indicated by URL, which is used on the Internet?', CAST(N'2021-02-01' AS Date), N'PRJ321', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE8', N'Identify correct statement about a WAR file', CAST(N'2021-02-01' AS Date), N'PRJ321', 1)
INSERT [dbo].[Questions] ([questionID], [question_content], [createDate], [subjectID], [status]) VALUES (N'QE9', N'A parameter is defined in a <context-param> element of the deployment descriptor for a web application.', CAST(N'2021-02-01' AS Date), N'PRJ321', 1)

INSERT [dbo].[Subjects] ([subjectID], [subjectName], [timeQuiz], [numberQuestion], [status]) VALUES (N'PRJ311', N'Java DESKTOP', 5, 10, 1)
INSERT [dbo].[Subjects] ([subjectID], [subjectName], [timeQuiz], [numberQuestion], [status]) VALUES (N'PRJ321', N'Java WEB', 5, 10, 1)
INSERT [dbo].[Subjects] ([subjectID], [subjectName], [timeQuiz], [numberQuestion], [status]) VALUES (N'PRO192', N'Java', 5, 10, 1)