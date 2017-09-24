package comp1110.ass2;

import java.util.*;

/**
 * This class provides the text interface for the Steps Game
 *
 * The game is based directly on Smart Games' IQ-Steps game
 * (http://www.smartgames.eu/en/smartgames/iq-steps)
 */
public class StepsGame {

    /**
     * Determine whether a piece placement is well-formed according to the following:
     * - it consists of exactly three characters
     * - the first character is in the range A .. H (shapes)
     * - the second character is in the range A .. H (orientations)
     * - the third character is in the range A .. Y and a .. y (locations)
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        // FIXME Task 2: determine whether a piece placement is well-forme
        //Check if it consists of exactly three characters
        if (piecePlacement.length() == 3) {

            char[] placementChar = piecePlacement.toCharArray();
            if(placementChar[0] >= 'A' && placementChar[0] <= 'H'){                    //Check the first char
                if(placementChar[1] >= 'A' && placementChar[1] <= 'H'){                //Check the second char
                    return ((placementChar[2] >= 'A' && placementChar[2] <= 'Y') ||    //Check the third char
                            (placementChar[2] >= 'a' && placementChar[2] <= 'y'));
                }
            }
        } return false;
    }


    /**
     * Determine whether a placement string is well-formed:
     *  - it consists of exactly N three-character piece placements (where N = 1 .. 8);
     *  - each piece placement is well-formed
     *  - no shape appears more than once in the placement
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     */
    static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        if (placement == null || placement.equals("")) {
            return false;
        } else if (placement.length() % 3 != 0) {
            return false;
        } else {
            String[] a = new String[placement.length() / 3];

            for (int i = 0; i < placement.length() / 3; i++) {
                a[i] = placement.substring(3 * i, 3 * i + 3);
                if (isPiecePlacementWellFormed(a[i])&& NoDuplicate(placement)) {
                    return true;

                }

                }

            }

        return false;
    }

// write a new method to check whether they are duplicate
    static boolean NoDuplicate (String placement) {
        boolean Duplicate = true;
        char [] b = placement.toCharArray();
        for (int i = 0; i < b.length ; i += 3)
            for (int k = 0; k < b.length ; k += 3)
                if (i!=k && b[i]==b[k])
                    Duplicate = false;
            return Duplicate;
    }


    /**
     * Determine whether a placement sequence is valid.  To be valid, the placement
     * sequence must be well-formed and each piece placement must be a valid placement
     * (with the pieces ordered according to the order in which they are played).
     *
     * @param placement A placement sequence string
     * @return True if the placement sequence is valid
     */
    static boolean isPlacementSequenceValid(String placement) {
        // FIXME Task 5: determine whether a placement sequence is valid
        if (!isPlacementWellFormed(placement)) {
            return false;
        } else {
            String[] place = new String[placement.length() / 3];

            for (int i = 0; i < placement.length() / 3; i++) {
                place[i] = placement.substring(3 * i, 3 * i + 3);
            }

            /* check the pieces are not in the outside of the board. */
            // check the home rings are not at the corner
            for (int j = 0; j < place.length; j += 3){
                if (place[j].charAt(2) == 'A' || place[j].charAt(2) == 'J' || place[j].charAt(2) == 'p' || place[j].charAt(2) == 'y' ){
                    return false;
                }
            }

            // check the home rings are not at the edge
            for (int m = 0; m < place.length; m++){
                if ((!isValidPieceString(place[m]))){
                    return false;
                }
            }

            // check the rings without overlap
           
        }
        return true;
    }


    static boolean isValidPieceString(String c) {
        // upper edge
        if (c.charAt(2) >= 'B' && c.charAt(2) <= 'I'){
            if (c.charAt(0) == 'B' && c.charAt(1) == 'B' || c.charAt(0) == 'B' && c.charAt(1) == 'H' ||
                    c.charAt(0) == 'E' && c.charAt(1) == 'D' || c.charAt(0) == 'E' && c.charAt(1) == 'F'){
                return  true;
            }else{
                return  false;
            }
        }   // right edge
        else if (c.charAt(2) == 'T' || c.charAt(2) == 'e' || c.charAt(2) == 'o'){
            if (c.charAt(0) == 'B' && c.charAt(1) == 'C' || c.charAt(0) == 'B' && c.charAt(1) == 'E' ||
                    c.charAt(0) == 'E' && c.charAt(1) == 'A' || c.charAt(0) == 'E' && c.charAt(1) == 'G'){
                return  true;
            }else{
                return  false;
            }
        }   // lower edge
        else if (c.charAt(2) >= 'q' && c.charAt(2) <= 'x'){
            if (c.charAt(0) == 'B' && c.charAt(1) == 'D' || c.charAt(0) == 'B' && c.charAt(1) == 'F' ||
                    c.charAt(0) == 'E' && c.charAt(1) == 'B' || c.charAt(0) == 'E' && c.charAt(1) == 'H'){
                return  true;
            }else{
                return  false;
            }
        }   // left edge
        else if (c.charAt(2) == 'K' || c.charAt(2) == 'U' || c.charAt(2) == 'f'){
            if (c.charAt(0) == 'B' && c.charAt(1) == 'A' || c.charAt(0) == 'B' && c.charAt(1) == 'G' ||
                    c.charAt(0) == 'E' && c.charAt(1) == 'C' || c.charAt(0) == 'E' && c.charAt(1) == 'E'){
                return  true;
            }else{
                return  false;
            }
        }return  true;
    }

/*
    static ArrayList<Integer> getCoordinate(String n){
        return null;
    }
    */

        /**
         * Given a string describing a placement of pieces and a string describing
         * an (unordered) objective, return a set of all possible next viable
         * piece placements.   A viable piece placement must be a piece that is
         * not already placed (ie not in the placement string), and which will not
         * obstruct any other unplaced piece.
         *
         * @param placement A valid sequence of piece placements where each piece placement is drawn from the objective
         * @param objective A valid game objective, but not necessarily a valid placement string
         * @return An set of viable piece placements
         */
    static Set<String> getViablePiecePlacements(String placement, String objective) {
        // FIXME Task 6: determine the correct order of piece placements
        /* for no more pieces */
        if(placement.length() == objective.length()) {
            return new HashSet<>();
        }

        return null;
    }

    /**
     * Return an array of all solutions to the game, given a starting placement.
     *
     * @param placement  A valid piece placement string.
     * @return An array of strings, each describing a solution to the game given the
     * starting point provided by placement.
     */
    static String[] getSolutions(String placement) {
        // FIXME Task 9: determine all solutions to the game, given a particular starting placement
        return null;
    }
}
