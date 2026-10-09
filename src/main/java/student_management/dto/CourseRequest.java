package student_management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CourseRequest {

    @NotBlank(message = "Course name is required")
    private String name;

    @Min(value = 1, message = "Duration must be greater than 0")
    private int duration;

    @Min(value = 1, message = "Fees must be greater than 0")
    private double fees;

    public CourseRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }
}