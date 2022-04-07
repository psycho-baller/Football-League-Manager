package manager;
/*
 * @authors Rami Maalouf, Max kaczmarek
 * @TUT T02, T01
 * @date 2022-4-7
 */
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
