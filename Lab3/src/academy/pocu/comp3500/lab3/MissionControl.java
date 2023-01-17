package academy.pocu.comp3500.lab3;

import java.util.ArrayList;

public final class MissionControl {
    private MissionControl() {
    }

    public static int findMaxAltitudeTime(final int[] altitudes) {
        int index = findIndexMaxAltitudeTimeRecursive(altitudes, 0, altitudes.length - 1);
        return altitudes[index];
    }

    private static int findIndexMaxAltitudeTimeRecursive(final int[] altitudes, int start, int end) {
        if (end - start <= 1) {
            return altitudes[end] > altitudes[start] ? end : start;
        }

        int mid = (start + end) / 2;

        if (altitudes[start] <= altitudes[mid]) {
            return findIndexMaxAltitudeTimeRecursive(altitudes, mid + 1, end);
        } else {
            return findIndexMaxAltitudeTimeRecursive(altitudes, start, mid - 1);
        }
    }

    public static ArrayList<Integer> findAltitudeTimes(final int[] altitudes, final int targetAltitude) {
        ArrayList<Integer> result = new ArrayList<>(32);
        int index = findIndexMaxAltitudeTimeRecursive(altitudes, 0, altitudes.length - 1);

        if (index < altitudes.length - 1) {
            findTargetAltitudeRecursive(altitudes, 0, index, targetAltitude, result);
            findTargetAltitudeRecursive(altitudes, index + 1, altitudes.length - 1, targetAltitude, result);
        } else {
            findTargetAltitudeRecursive(altitudes, 0, altitudes.length - 1, targetAltitude, result);
        }

        return result;
    }

    private static void findTargetAltitudeRecursive(final int[] altitudes, int start, int end, final int targetAltitude, ArrayList<Integer> out) {
        if (start >= end) {
            if (altitudes[start] == targetAltitude) {
                out.add(start);
            }

            return;
        }

        int mid = (start + end) / 2;

        if (altitudes[mid] == targetAltitude) {
            out.add(mid);
            return;
        }

        if (altitudes[start] < altitudes[end]) {
            if (altitudes[mid] > targetAltitude) {
                findTargetAltitudeRecursive(altitudes, start, mid - 1, targetAltitude, out);
            } else {
                findTargetAltitudeRecursive(altitudes, mid + 1, end, targetAltitude, out);
            }
        } else {
            if (altitudes[mid] > targetAltitude) {
                findTargetAltitudeRecursive(altitudes, mid + 1, end, targetAltitude, out);
            } else {
                findTargetAltitudeRecursive(altitudes, start, mid - 1, targetAltitude, out);
            }
        }
    }
}