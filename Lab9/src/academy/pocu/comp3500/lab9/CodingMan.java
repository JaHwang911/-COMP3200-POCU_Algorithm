package academy.pocu.comp3500.lab9;

import academy.pocu.comp3500.lab9.data.VideoClip;

import java.util.ArrayList;

public class CodingMan {
    public static int findMinClipsCount(final VideoClip[] clips, int time) {
        if (clips.length == 0) {
            return -1;
        }

        clipsSortByStartTimeRecursive(clips, 0, clips.length - 1);

        if (clips[0].getStartTime() != 0) {
            return -1;
        }

        ArrayList<VideoClip> preprocessed = getMaxRunningTimeClips(clips);
        ArrayList<VideoClip> resultClips = new ArrayList<>(preprocessed.size());

        int i = 0;

        for (; i < preprocessed.size(); ++i) {
            int nextIndex = i;
            int maxRunningTime = 0;

            for (int j = i + 1; j < preprocessed.size(); ++j) {
                int runningTime = preprocessed.get(j).getEndTime() - preprocessed.get(j).getStartTime();

                if (preprocessed.get(i).getEndTime() >= preprocessed.get(j).getStartTime()) {
                    if (maxRunningTime < runningTime) {
                        nextIndex = j;
                    }
                } else {
                    break;
                }
            }

            if (resultClips.size() == 0 || resultClips.get(resultClips.size() - 1).getEndTime() >= preprocessed.get(i).getStartTime()) {
                resultClips.add(preprocessed.get(i));

                if (preprocessed.get(i).getEndTime() >= time) {
                    break;
                }
            }

            if (nextIndex != i) {
                i = nextIndex - 1;
            }
        }

        if (resultClips.get(resultClips.size() - 1).getEndTime() < time) {
            return -1;
        }

        return resultClips.size();
    }

    public static ArrayList<VideoClip> getMaxRunningTimeClips(final VideoClip[] clips) {
        ArrayList<VideoClip> preprocessedClips = new ArrayList<>(clips.length);

        for (int i = 0; i < clips.length; ++i) {
            int index = i;
            int maxRunningTime = clips[i].getEndTime() - clips[i].getStartTime();

            for (int j = i + 1; j < clips.length; ++j) {
                int runningTime = clips[j].getEndTime() - clips[j].getStartTime();

                if (clips[i].getStartTime() == clips[j].getStartTime()) {
                    if (maxRunningTime < runningTime) {
                        index = j;
                        maxRunningTime = runningTime;
                    }
                } else {
                    break;
                }
            }

            preprocessedClips.add(clips[index]);
            i = index;
        }

        return preprocessedClips;
    }

    public static void clipsSortByStartTimeRecursive(final VideoClip[] clips, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left;
        for (int j = left; j < right; ++j) {
            if (clips[j].getStartTime() == clips[right].getStartTime() && clips[j].getEndTime() < clips[right].getEndTime()) {
                VideoClip temp = clips[j];
                clips[j] = clips[i];
                clips[i] = temp;

                ++i;
            } else if (clips[j].getStartTime() < clips[right].getStartTime()) {
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