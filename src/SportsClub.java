/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public abstract class SportsClub {
    
    private String name;
    private String statistics;

    public SportsClub(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        return this.name.equals(((SportsClub)object).name);
    }

    public String getName() {
        return name;
    }
    

    public String getStatistics() {
        return statistics;
    }
    

    public void setName(String s) {
        this.name = s;
    } 
    
    public void setStatistics(String s) {
        this.statistics = s;
    }



}
