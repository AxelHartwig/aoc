import java.util.List;

public class Combination {
        String state;
        List<Integer> groupings;

        Combination(String state, List<Integer> groupings) {
            this.state = state;
            this.groupings = groupings;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if(!(obj instanceof Combination)) {
                return false;
            }

            Combination comb = (Combination) obj;

            if(!this.state.equals(comb.state)) {
                return false;
            }

            if(!this.groupings.equals(comb.groupings)) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = 7;
            result = 31 * result + groupings.size();
            result = 31 * result + state.length();
            return result;
        }

    }
