package cn.edu.hust.model;

/**
 * Created by lxiao on 2017/2/17.
 */
public class DoctorThesisApplyPartStatus {
    private  int part1;
    private  int part2;

    public int getPart1() {
        return part1;
    }

    public void setPart1(int part1) {
        this.part1 = part1;
    }

    public int getPart2() {
        return part2;
    }

    public void setPart2(int part2) {
        this.part2 = part2;
    }

    @Override
    public String toString() {
        return "DoctorThesisApplyPartStatus{" +
                "part1=" + part1 +
                ", part2=" + part2 +
                '}';
    }
}
