package com.example.geektrust;


import java.util.LinkedHashSet;

public class Node {// TODO Add generics wherever possible

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

    boolean assignPartnerIfSingle(String newPartnerName) {
        if (newPartnerName == null) return false;
        if (this.partnerName == null || this.partnerName.equals(newPartnerName)) { // Allows to Set name once and guards against changing it
            this.partnerName = newPartnerName;
            return true;
        } else return false;
    }

    boolean addKidToMother(String kid) {
        if (kid==null || kid.isBlank() ) return false;
        if (this.gender == Gender.FEMALE) {
            this.kids.add(kid);
            return true;
        }
        return false;
    }

    boolean assignMotherIfKidHasNoMother(String newMotherName) {
        if (newMotherName == null || newMotherName.isBlank()) return false;
        if (this.motherName == null || this.motherName.equals(newMotherName)) {  // Allows to Set name once and guards against changing it
            this.motherName = newMotherName;
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
