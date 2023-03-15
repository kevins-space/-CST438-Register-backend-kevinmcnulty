package com.cst438.domain;

public class StudentDTO {

    private String email;
    private String name;
    private int studentId;
    private String status;

    public StudentDTO() {}

    public StudentDTO(String email, String name, int studentId, boolean onHold) {
        this.email = email;
        this.name = name;
        this.studentId = studentId;
        setStatus(onHold);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(boolean onHold) {
        if (onHold) {
            this.status = "onHold";
        } else {
            this.status = "notOnHold";
        }
    }

    @Override
    public String toString() {
        return "StudentDTO [email=" + email + ", name=" + name + ", studentId=" + studentId + ", status=" + status + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentDTO other = (StudentDTO) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (studentId != other.studentId)
            return false;
        return true;
    }

}
