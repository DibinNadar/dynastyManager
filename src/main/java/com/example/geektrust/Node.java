package com.example.geektrust;



import java.util.HashSet;
// TODO throws null when invalid values called via Dynasty!!
public class Node {

    private final String name;
    private final Gender gender;
    private String partnerName;
    private String motherName;
    private HashSet<String> kids;

    public Node(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        this.partnerName = null;
        this.motherName = null;
        this.kids = new HashSet<>();
    }


//      TODO: Check if I should use this here or elsewhere , Can getter setter be private?
      public boolean addPartnerNameOnlyOnce(String partnerName) {
        if (this.partnerName == null || this.partnerName.equals(partnerName)){ // Allows for Set once and also repeat, so False-false is not returned
            this.partnerName = partnerName;
            return true;
        }else return false;

//      this.partnerName = this.partnerName == null ? partnerName : this.partnerName;
      }


// TODO: Check if I should use this here or elsewhere , Can getter setter be private?
    public void addKidToMother(String kid) {
        if (this.gender == Gender.FEMALE) this.kids.add(kid);
    }

    public boolean addMotherOnlyOnce(String motherName){  // BETTER NAME!!!
            if (this.motherName == null || this.motherName.equals(motherName)){
                this.motherName = motherName;
                return true;
            }else return false;

        }


    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public String getMotherName() {
        return motherName;
    }

    public HashSet<String> getKids() {
        return kids;
    }

    // TODO override to string
}
