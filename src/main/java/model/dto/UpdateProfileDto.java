package model.dto;

public class UpdateProfileDto {

    private final long id;
    private final String fullName;
    private final String phoneNumber;

    public UpdateProfileDto(long id, String fullName, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
