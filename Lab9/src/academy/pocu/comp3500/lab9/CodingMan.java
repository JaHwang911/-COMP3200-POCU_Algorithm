package academy.pocu.comp3500.lab9;

import academy.pocu.comp3500.lab9.data.VideoClip;

import java.util.ArrayList;

public class CodingMan {
    public static int findMinClipsCount(final VideoClip[] clips, int time) {
        assert (time > 0);
        clipsSortByStartTimeRecursive(clips, 0, clips.length - 1);

        if (clips[0].getStartTime() != 0) {
            return -1;
        }

        ArrayList<VideoClip> resultClips = new ArrayList<>(clips.length);
        int currentClipsStartTime = 0;
        int selectedClipsIndex = 0;
        int currentClipsMaxRunningTime = clips[0].getEndTime();

        for (int i = 1; i < clips.length; ++i) {
            int runningTime = clips[i].getEndTime() - clips[i].getStartTime();

            if (currentClipsStartTime == clips[i].getStartTime()) {
                if (currentClipsMaxRunningTime < runningTime) {
                    currentClipsMaxRunningTime = runningTime;
                    selectedClipsIndex = i;
                }
            } else if (clips[selectedClipsIndex].getEndTime() >= clips[i].getStartTime()) {
                resultClips.add(clips[selectedClipsIndex]);
            }
        }

        if (resultClips.get(resultClips.size() - 1).getEndTime() < time) {
            return -1;
        }

        return resultClips.size();
    }

    private static void clipsSortByStartTimeRecursive(final VideoClip[] clips, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left;
        for (int j = left; j < right; ++j) {
            if (clips[j].getStartTime() < clips[right].getStartTime()) {
                VideoClip temp = clips[j];
                clips[j] = clips[i];
                clips[i] = temp;

                ++i;
            }
        }

        VideoClip temp = clips[i];
        clips[i] = clips[right];
        clips[right] = temp;

        clipsSortByStartTimeRecursive(clips, left, i - 1);
        clipsSortByStartTimeRecursive(clips, i + 1, right);
    }
}