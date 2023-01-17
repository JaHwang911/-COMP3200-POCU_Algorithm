package academy.pocu.comp3500.lab3;

import java.util.ArrayList;

public class Test {
    private Test() {

    }

    public static void testFindMaxAltitudeTime() {
        {
            int[] altitudes = new int[] { 1, 2, 3, 4, 5, 6, 7, 4, 3, 2 };

            int maxAltitude = MissionControl.findMaxAltitudeTime(altitudes);
            assert (maxAltitude == 7);
        }

        {
            int[] altitudes = new int[] { 1, 2, 3, 4, 5, 7, 6, 4, 3, 2 };

            int maxAltitude = MissionControl.findMaxAltitudeTime(altitudes);
            assert (maxAltitude == 7);
        }

        {
            int[] altitudes = new int[] { 1, 2, 3, 4, 5, 7, 6, 4, 3 };

            int maxAltitude = MissionControl.findMaxAltitudeTime(altitudes);
            assert (maxAltitude == 7);
        }

        {
            int[] altitudes = new int[] { 1, 2, 3, 4, 5, 7, 8, 6, 4, 3, 2 };

            int maxAltitude = MissionControl.findMaxAltitudeTime(altitudes);
            assert (maxAltitude == 8);
        }

        {
            int[] altitudes = new int[] { 1, 2, 3, 4, 5, 6, 7 };

            int maxAltitude = MissionControl.findMaxAltitudeTime(altitudes);
            assert (maxAltitude == 7);
        }

        {
            int[] altitudes = new int[] { 7, 6, 5, 4, 3, 2, 1 };

            int maxAltitude = MissionControl.findMaxAltitudeTime(altitudes);
            assert (maxAltitude == 7);
        }
    }

    public static void testFindAltitudeTime() {
        {
            final int[] altitudes = new int[] { 1, 2, 3, 4, 5, 6, 7, 4, 3, 2 };

            ArrayList<Integer> bounds = MissionControl.findAltitudeTimes(altitudes, 2);

            assert (bounds.size() == 2);

            assert (bounds.get(0) == 1);
            assert (bounds.get(1) == 9);

            bounds = MissionControl.findAltitudeTimes(altitudes, 5);

            assert (bounds.size() == 1);
            assert (bounds.get(0) == 4);

            bounds = MissionControl.findAltitudeTimes(altitudes, 12);
            assert (bounds.size() == 0);
        }

        {
            final int[] altitudes = new int[] { 1 };
            ArrayList<Integer> bounds = MissionControl.findAltitudeTimes(altitudes, 2);

            assert (bounds.size() == 0);

            bounds = MissionControl.findAltitudeTimes(altitudes, 1);

            assert (bounds.size() == 1);
            assert (bounds.get(0) == 0);
        }

        {
            final int[] altitudes = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23 };
            ArrayList<Integer> bounds = MissionControl.findAltitudeTimes(altitudes, 19);

            assert (bounds.size() == 1);
            assert (bounds.get(0) == 7);

            bounds = MissionControl.findAltitudeTimes(altitudes, 18);

            assert (bounds.size() == 0);
        }

        {
            final int[] altitudes = new int[] { 23, 19, 17, 13, 11, 7 , 5, 3, 2 };
            ArrayList<Integer> bounds = MissionControl.findAltitudeTimes(altitudes, 19);

            assert (bounds.size() == 1);
            assert (bounds.get(0) == 1);

            bounds = MissionControl.findAltitudeTimes(altitudes, 18);

            assert (bounds.size() == 0);
        }
    }
}
