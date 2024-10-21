Find more information about the product here:
https://docs.google.com/document/d/1TiqnK-dHDTA4fGpXaYR2Ihd31UrfyF3VQazxa2hi3Uo/edit

AI Generated Documentation:
Developing a web application for 1:1 tuition sessions involves designing various components, including REST APIs, user authentication and authorization, classes, and database tables. Here are the answers to your questions:

### 1. REST APIs

#### User Management
- **Register User**: `POST /api/users/register`
- **Login User**: `POST /api/users/login`
- **Get User Profile**: `GET /api/users/{userId}`
- **Update User Profile**: `PUT /api/users/{userId}`
- **Delete User**: `DELETE /api/users/{userId}`

#### Student Management
- **Raise Request for Teacher**: `POST /api/students/{studentId}/request-teacher`
- **Upload Syllabus**: `POST /api/students/{studentId}/syllabus`
- **View Syllabus**: `GET /api/students/{studentId}/syllabus`
- **View Progress**: `GET /api/students/{studentId}/progress`

#### Teacher Management
- **Assign Teacher**: `POST /api/admin/assign-teacher`
- **View Assigned Students**: `GET /api/teachers/{teacherId}/students`
- **Mark Topic Completed**: `PUT /api/teachers/{teacherId}/students/{studentId}/topics/{topicId}/complete`
- **Conduct Test**: `POST /api/teachers/{teacherId}/students/{studentId}/test`
- **Get Test Results**: `GET /api/teachers/{teacherId}/students/{studentId}/test-results`

#### Parent Management
- **Monitor Progress**: `GET /api/parents/{parentId}/students/{studentId}/progress`
- **Get Test Results**: `GET /api/parents/{parentId}/students/{studentId}/test-results`

#### Admin Management
- **Contact User**: `POST /api/admin/contact-user`
- **View All Users**: `GET /api/admin/users`
- **Manage Users**: `PUT /api/admin/users/{userId}`

### 2. User Authentication and Authorization

#### Roles
- **Student**: Can request a teacher, upload syllabus, view their progress.
- **Teacher**: Can view assigned students, mark topics completed, conduct tests.
- **Parent**: Can monitor student's progress and view test results.
- **Admin**: Can manage users, assign teachers, and contact users.

#### Authentication
- Use JWT (JSON Web Token) for authentication.
- Implement login endpoint to issue JWT on successful login.
- Secure endpoints using JWT-based authorization.

#### Authorization
- Use role-based access control (RBAC).
- Assign roles during user registration or by admin.
- Secure endpoints based on roles.

### 3. Classes Supporting the REST API

#### User Class
```java
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    // getters and setters
}
```

#### Student Class
```java
public class Student extends User {
    private List<Syllabus> syllabus;
    private Progress progress;
    // getters and setters
}
```

#### Teacher Class
```java
public class Teacher extends User {
    private List<Student> assignedStudents;
    // getters and setters
}
```

#### Parent Class
```java
public class Parent extends User {
    private List<Student> children;
    // getters and setters
}
```

#### Admin Class
```java
public class Admin extends User {
    // Admin-specific fields and methods
}
```

#### Syllabus Class
```java
public class Syllabus {
    private Long id;
    private List<Topic> topics;
    // getters and setters
}
```

#### Topic Class
```java
public class Topic {
    private Long id;
    private String name;
    private boolean completed;
    // getters and setters
}
```

#### Test Class
```java
public class Test {
    private Long id;
    private List<Question> questions;
    private List<TestResult> results;
    // getters and setters
}
```

#### TestResult Class
```java
public class TestResult {
    private Long id;
    private Long studentId;
    private Long testId;
    private int score;
    // getters and setters
}
```

### 4. Database Tables

#### users Table
```sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);
```

#### students Table
```sql
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
```

#### teachers Table
```sql
CREATE TABLE teachers (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
```

#### parents Table
```sql
CREATE TABLE parents (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
```

#### syllabus Table
```sql
CREATE TABLE syllabus (
    id SERIAL PRIMARY KEY,
    student_id INTEGER NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students (id)
);
```

#### topics Table
```sql
CREATE TABLE topics (
    id SERIAL PRIMARY KEY,
    syllabus_id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    completed BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (syllabus_id) REFERENCES syllabus (id)
);
```

#### tests Table
```sql
CREATE TABLE tests (
    id SERIAL PRIMARY KEY,
    teacher_id INTEGER NOT NULL,
    student_id INTEGER NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES teachers (id),
    FOREIGN KEY (student_id) REFERENCES students (id)
);
```

#### test_results Table
```sql
CREATE TABLE test_results (
    id SERIAL PRIMARY KEY,
    test_id INTEGER NOT NULL,
    student_id INTEGER NOT NULL,
    score INTEGER NOT NULL,
    FOREIGN KEY (test_id) REFERENCES tests (id),
    FOREIGN KEY (student_id) REFERENCES students (id)
);
```

This design should provide a comprehensive framework for your web application, covering user registration, session management, syllabus tracking, progress monitoring, and role-based access control.
