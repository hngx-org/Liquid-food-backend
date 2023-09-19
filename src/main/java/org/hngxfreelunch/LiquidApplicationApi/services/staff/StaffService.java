package org.hngxfreelunch.LiquidApplicationApi.services.staff;


import org.hngxfreelunch.LiquidApplicationApi.data.entities.Staff;

public interface StaffService {
    // TODO: ADD NEW STAFF TO TEAM
    public void addStaff(Staff staff);
    // TODO: GET STAFF DETAILS
    public Staff getStaff(Long id);
    // TODO: UPDATE STAFF DETAILS
    public void updateStaff(Long id, Staff updatedStaff);
}
