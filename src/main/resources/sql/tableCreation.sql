CREATE TABLE employee (
    employee_id INT NOT NULL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    department_id INT,
    job_title VARCHAR(255),
    gender VARCHAR(255),
    date_of_birth DATE
);