package main.java.space;

public class Galaxy {
    private String name;
    private boolean isExists;

    public Galaxy(String name) {
        this.name = name;
        isExists = true;
    }

    public String getGalaxyName() {
        if (isExists) {
            return name;
        }
        return "Галактики не существует";
    }

    public int destroyGalaxy() {
        if (isExists) {
            class BlackHole {
                void collapsed() {
                    System.out.println("Черная дыра в центре галактики " + getGalaxyName() +
                            " схлопнулась, уничтожив при этом всё живое.");
                    isExists = false;
                }
            }

            BlackHole blackHole = new BlackHole();
            blackHole.collapsed();
            return 0;
        }
        return 1;
    }

    public static class PhysicsLaws {
        private PhysicsLaws() {
            System.out.println("Как?!");
        }
        public static final Double GRAVITATIONAL_CONSTANT = 6.67E-11;

        public static double calculateFreeFallAcceleration(Double mass, Double radius) {
            return GRAVITATIONAL_CONSTANT * (mass / (radius * 1000 * radius * 1000));
        }
    }

    public class PlanetarySystem {
        private String name;

        public PlanetarySystem(String name) {
            this.name = name;
        }

        public String getPlanetarySystemName() {
            if (isExists) {
                return name + " из галактики " + getGalaxyName();
            }
            return "галактики не существует";
        }
    }
}
