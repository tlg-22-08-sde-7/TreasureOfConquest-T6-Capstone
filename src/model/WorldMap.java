package model;

import java.util.List;

public class WorldMap {

    // Properties
    private List<? extends Countries> countries;

    // No-arg Ctor
    public WorldMap() {

    }

    // Countries
    public static class Countries {
        // Properties
        private String name;
        private int zone;
        private int cost;
        private List<? extends FunFacts> funFacts;
        private List<? extends Attractions> attractions;
        private List<? extends Restaurants> restaurants;
        private List<? extends WeaponStores> weaponStores;

        public Countries() {
        }

        // Class Types
        public static class FunFacts {
            //Properties
            private String text;
            private String answer;

            // No-arg ctor
            public FunFacts() {
            }

            // Getters and Setters
            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }
        }

        public static class Attractions {
            // Properties
            private String name;
            private String type;
            private String location;
            private int cost;
            private List<? extends Treasures> treasures;
            private List<? extends Riddles> riddles;

            // No-arg ctor
            public Attractions() {
            }

            public static class Treasures {
                private String name;
                private int value;

                public Treasures() {};

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            };

            public static class Riddles {
                private String text;
                private List<String> options;
                private String answer;

                // No-arg ctor
                public Riddles() {
                };

                // Getters and Setters
                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public List<String>  getOptions() {
                    return options;
                }

                public void setOptions(List<String>  options) {
                    this.options = options;
                }

                public String getAnswer() {
                    return answer;
                }

                public void setAnswer(String answer) {
                    this.answer = answer;
                }
            }

            // Getters and Setters
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public Integer getCost() {
                return cost;
            }

            public void setCost(int cost) {
                this.cost = cost;
            }

            public List<? extends Treasures> getTreasures() {
                return treasures;
            }

            public void setTreasures(List<? extends Treasures> treasures) {
                this.treasures = treasures;
            }

            public List<? extends Riddles> getRiddles() {
                return riddles;
            }

            public void setRiddles(List<? extends Riddles> riddles) {
                this.riddles = riddles;
            }
        }

        public static class Restaurants {
            // Properties
            private String name;
            private String type;
            private List<Items> items;

            // No-arg ctor
            public Restaurants() {
            }

            // Template
            public static class Items {
                // Properties
                private String name;
                private Integer cost;
                private Integer value;

                // No-arg ctor
                public Items() {
                }

                // Getters and Setters
                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Integer getCost() {
                    return cost;
                }

                public void setCost(Integer cost) {
                    this.cost = cost;
                }

                public Integer getValue() {
                    return value;
                }

                public void setValue(Integer value) {
                    this.value = value;
                }
            }

            // Getters and Setters
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<Items> getItems() {
                return items;
            }

            public void setItems(List<Items> items) {
                this.items = items;
            }
        }

        private static class WeaponStores {
            // Properties
            private String name;
            private List<Weapons> weapons;

            // No-arg ctor
            public WeaponStores() {
            }

            // Template
            public static class Weapons {
                private String name;
                private int cost;
                private int damage;

                // No-arg ctor
                public Weapons() {
                }

                // Getters and Setters
                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public Integer getCost() {
                    return cost;
                }

                public void setCost(int cost) {
                    this.cost = cost;
                }

                public Integer getDamage() {
                    return damage;
                }

                public void setDamage(int damage) {
                    this.damage = damage;
                }
            }

            // Getters and Setters
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<Weapons> getWeapons() {
                return weapons;
            }

            public void setWeapons(List<Weapons> weapons) {
                this.weapons = weapons;
            }
        }

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getZone() {
            return zone;
        }

        public void setZone(int zone) {
            this.zone = zone;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public List<? extends FunFacts> getFunFacts() {
            return funFacts;
        }

        public void setFunFacts(List<? extends FunFacts> funFacts) {
            this.funFacts = funFacts;
        }

        public List<? extends Attractions> getAttractions() {
            return attractions;
        }

        public void setAttractions(List<? extends Attractions> attractions) {
            this.attractions = attractions;
        }

        public List<? extends Restaurants> getRestaurants() {
            return restaurants;
        }

        public void setRestaurants(List<? extends Restaurants> restaurants) {
            this.restaurants = restaurants;
        }

        public List<? extends WeaponStores> getWeaponStores() {
            return weaponStores;
        }

        public void setWeaponStores(List<? extends WeaponStores> weaponStores) {
            this.weaponStores = weaponStores;
        }
    }

    // Getters and Setters
    public List<? extends Countries> getCountries() {
        return countries;
    }

    public void setCountries(List<? extends Countries> countries) {
        this.countries = countries;
    }
}