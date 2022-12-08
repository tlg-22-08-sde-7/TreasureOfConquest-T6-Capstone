package model;

public class WorldMap {

    // Properties
    private Mexico mexico;
    private Japan japan;

    // No-arg Ctor
    public WorldMap() {

    }

    // Template
    public static class CountriesStructure {
        /*
         * Countries should extend this class
         */

        // Properties
        private Meta meta;
        private Attraction1 attraction1;
        private Restaurant1 restaurant1;
        private Restaurant2 restaurant2;
        private WeaponStore1 weaponStore1;

        // Meta
        public static class Meta {
            // Properties
            private String name;
            private Integer zone;
            private Integer cost;
            private FunFacts funFacts;

            // No-arg Ctor
            public Meta() {};

            public static class FunFacts {
                private FunFact1 funFact1;
                private FunFact2 funFact2;

                public FunFacts() {};

                private static class FunFactsStructure {
                    private String text;
                    private String answer;

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

                public static class FunFact1 extends FunFactsStructure {
                    public FunFact1() {};
                }

                public static class FunFact2 extends FunFactsStructure {
                    public FunFact2() {};
                }

                public FunFact1 getFunFact1() {
                    return funFact1;
                }

                public void setFunFact1(FunFact1 funFact1) {
                    this.funFact1 = funFact1;
                }

                public FunFact2 getFunFact2() {
                    return funFact2;
                }

                public void setFunFact2(FunFact2 funFact2) {
                    this.funFact2 = funFact2;
                }

            }

            // Getters and Setters
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getZone() {
                return zone;
            }

            public void setZone(Integer zone) {
                this.zone = zone;
            }

            public Integer getCost() {
                return cost;
            }

            public void setCost(Integer cost) {
                this.cost = cost;
            }

            public FunFacts getFunFacts() {
                return funFacts;
            }

            public void setFunFacts(FunFacts funFacts) {
                this.funFacts = funFacts;
            }
        }

        // Attractions
        private static class AttractionsStructure {
            private String name;
            private String location;
            private Integer cost;
            private Treasures treasures;
            private Riddles riddles;

            public static class Treasures {
                private Treasure1 treasure1;
                private Treasure2 treasure2;

                public Treasures() {};

                private static class TreasuresStructure {
                    private String name;
                    private Integer value;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public Integer getValue() {
                        return value;
                    }

                    public void setValue(Integer value) {
                        this.value = value;
                    }
                }

                public static class Treasure1 extends TreasuresStructure {
                    public Treasure1() {
                    }
                }

                public static class Treasure2 extends TreasuresStructure {
                    public Treasure2() {
                    }
                }

                public Treasure1 getTreasure1() {
                    return treasure1;
                }

                public void setTreasure1(Treasure1 treasure1) {
                    this.treasure1 = treasure1;
                }

                public Treasure2 getTreasure2() {
                    return treasure2;
                }

                public void setTreasure2(Treasure2 treasure2) {
                    this.treasure2 = treasure2;
                }


            };

            public static class Riddles {
                private Riddle1 riddle1;
                private Riddle2 riddle2;

                public Riddles() {
                };

                private static class RiddlesStructure {
                    private String text;
                    private String[] options;
                    private String answer;

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }

                    public String[] getOptions() {
                        return options;
                    }

                    public void setOptions(String[] options) {
                        this.options = options;
                    }

                    public String getAnswer() {
                        return answer;
                    }

                    public void setAnswer(String answer) {
                        this.answer = answer;
                    }
                }

                public static class Riddle1 extends RiddlesStructure {
                    public Riddle1() {
                    }
                }

                public static class Riddle2 extends RiddlesStructure {
                    public Riddle2() {
                    }
                }

                // Getters and Setters
                public Riddle1 getRiddle1() {
                    return riddle1;
                }

                public void setRiddle1(Riddle1 riddle1) {
                    this.riddle1 = riddle1;
                }

                public Riddle2 getRiddle2() {
                    return riddle2;
                }

                public void setRiddle2(Riddle2 riddle2) {
                    this.riddle2 = riddle2;
                }
            }

            // Getters and Setters
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public void setCost(Integer cost) {
                this.cost = cost;
            }

            public Treasures getTreasures() {
                return treasures;
            }

            public void setTreasures(Treasures treasures) {
                this.treasures = treasures;
            }

            public Riddles getRiddles() {
                return riddles;
            }

            public void setRiddles(Riddles riddles) {
                this.riddles = riddles;
            }
        }

        public static class Attraction1 extends AttractionsStructure {
            public Attraction1() {
            };
        }

        // Restaurants
        private static class RestaurantStructure {
            // Properties
            private String name;
            private Item1 item1;
            private Item2 item2;

            // Template
            private static class ItemStructure {
                // Properties
                private String name;
                private Integer cost;
                private Integer value;

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

            // Items
            public static class Item1 extends ItemStructure {
                public Item1() {
                }
            }

            public static class Item2 extends ItemStructure {
                public Item2() {
                }
            }

            // Getters and Setters
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Item1 getItem1() {
                return item1;
            }

            public void setItem1(Item1 item1) {
                this.item1 = item1;
            }

            public Item2 getItem2() {
                return item2;
            }

            public void setItem2(Item2 item2) {
                this.item2 = item2;
            }
        }

        public static class Restaurant1 extends RestaurantStructure {
            public Restaurant1() {
            }
        }

        public static class Restaurant2 extends RestaurantStructure {
            public Restaurant2() {
            }
        }

        // Weapons
        private static class WeaponStoreStructure {
            // Properties
            private String name;
            private Weapon1 weapon1;
            private Weapon2 weapon2;

            // Template
            private static class WeaponStructure {
                private String name;
                private Integer cost;
                private Integer damage;

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

                public Integer getDamage() {
                    return damage;
                }

                public void setDamage(Integer damage) {
                    this.damage = damage;
                }
            }

            // Weapons
            public static class Weapon1 extends WeaponStructure {
                public Weapon1() {
                }
            }

            public static class Weapon2 extends WeaponStructure {
                public Weapon2() {
                }
            }

            // Getters and Setters
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Weapon1 getWeapon1() {
                return weapon1;
            }

            public void setWeapon1(Weapon1 weapon1) {
                this.weapon1 = weapon1;
            }

            public Weapon2 getWeapon2() {
                return weapon2;
            }

            public void setWeapon2(Weapon2 weapon2) {
                this.weapon2 = weapon2;
            }
        }

        public static class WeaponStore1 extends WeaponStoreStructure {
            public WeaponStore1() {
            }
        }


        // Getters and Setters
        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }

        public Attraction1 getAttraction1() {
            return attraction1;
        }

        public void setAttraction1(Attraction1 attraction1) {
            this.attraction1 = attraction1;
        }

        public Restaurant1 getRestaurant1() {
            return restaurant1;
        }

        public void setRestaurant1(Restaurant1 restaurant1) {
            this.restaurant1 = restaurant1;
        }

        public Restaurant2 getRestaurant2() {
            return restaurant2;
        }

        public void setRestaurant2(Restaurant2 restaurant2) {
            this.restaurant2 = restaurant2;
        }

        public WeaponStore1 getWeaponStore1() {
            return weaponStore1;
        }

        public void setWeaponStore1(WeaponStore1 weaponStore1) {
            this.weaponStore1 = weaponStore1;
        }
    }

    // Countries
    public static class Mexico extends CountriesStructure {
        public Mexico() {
        }
    }

    public static class Japan extends CountriesStructure {
        public Japan() {
        }
    }

    // Getters and Setters
    public Mexico getMexico() {
        return mexico;
    }

    public void setMexico(Mexico mexico) {
        this.mexico = mexico;
    }

    public Japan getJapan() {
        return japan;
    }

    public void setJapan(Japan japan) {
        this.japan = japan;
    }
}