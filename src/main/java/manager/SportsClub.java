package manager;

public abstract class SportsClub {

    private final String name;

    public SportsClub(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        return this.name.equals(((SportsClub) object).name);
    }

    public String getName() {
        return name;
    }

}
