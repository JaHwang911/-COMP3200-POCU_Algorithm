package academy.pocu.comp3500.lab3;

import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {
        return maxAltitudeTimeRecursive(altitudes, 0, altitudes.length - 1);
    }

    private static int maxAltitudeTimeRecursive(final int[] altitudes, int start, int end) {
        if (end - start <= 1) {
            return Math.max(altitudes[start], altitudes[end]);
        }

        int mid = (start + end) / 2;

        if (altitudes[start] <= altitudes[mid]) {
            return maxAltitudeTimeRecursive(altitudes, mid + 1, end);
        } else {
            return maxAltitudeTimeRecursive(altitudes, start, mid - 1);
        }
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        ArrayList<Integer> result = new ArrayList<>(32);

        findTargetAltitudeRecursive(altitudes, 0, altitudes.length - 1, targetAltitude, result);

        return result;
    }

    private static void findTargetAltitudeRecursive(final int[] altitudes, int start, int end, final int targetAltitude, ArrayList<Integer> out) {
        if (end - start <= 1) {
            if (altitudes[start] == targetAltitude) {
                out.add(start);
            } else if (altitudes[end] == targetAltitude) {
                out.add(end);
            }

            return;
        }

        int mid = (start + end) / 2;

        if (altitudes[start] <= targetAltitude && altitudes[mid] > targetAltitude) {
            findTargetAltitudeRecursive(altitudes, start, mid - 1, targetAltitude, out);
        }

        if (altitudes[mid] > targetAltitude && altitudes[end] <= targetAltitude) {
            findTargetAltitudeRecursive(altitudes, mid + 1, end, targetAltitude, out);
        }
    }
}