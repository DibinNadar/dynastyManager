package com.example.geektrust;


import java.util.LinkedHashSet;

public class Node {

    private final String name;
    private final Gender gender;
    private String partnerName;
    private String motherName;
    private final LinkedHashSet<String> kids;

    public Node(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        this.partnerName = null;
        this.motherName = null;
        this.kids = new LinkedHashSet<>();
    }

    public boolean assignPartnerIfSingle(String partnerName) {
        if (this.partnerName == null || this.partnerName.equals(partnerName)) { // Allows to Set name once and guards against changing it
            this.partnerName = partnerName;
            return true;
        } else return false;
    }

    public void addKidToMother(String kid) {
        if (this.gender == Gender.FEMALE) this.kids.add(kid);
    }

    public boolean assignMotherIfMotherLess(String motherName) {
        if (this.motherName == null || this.motherName.equals(motherName)) {  // Allows to Set name once and guards against changing it
            this.motherName = motherName;
            return true;
        } else return false;

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
    public LinkedHashSet<String> getKids() {
        return kids;
    }
}
