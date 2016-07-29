package com.grotesque.saa.rank.data;

/**
 * Created by 0614_000 on 2015-05-29.
 */
public class LeagueTable {
    String rank;
    String team;
    String match;
    String win;
    String draw;
    String lose;
    String score;
    String nscore;
    String pm;
    String points;
    String detail;
    String ico;
    boolean selected = false;

    public LeagueTable(String rank, String team, String match, String win, String draw, String lose, String score, String nscore, String pm, String points) {
        this.rank = rank;
        this.team = team;
        this.match = match;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.score = score;
        this.nscore = nscore;
        this.pm = pm;
        this.points = points;
    }


    public LeagueTable(String rank, String team, String match, String win, String draw, String lose, String score, String nscore, String pm, String points, String detail) {
        this.rank = rank;
        this.team = team;
        this.match = match;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.score = score;
        this.nscore = nscore;
        this.pm = pm;
        this.points = points;
        this.detail = detail;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getLose() {
        return lose;
    }

    public void setLose(String lose) {
        this.lose = lose;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getNscore() {
        return nscore;
    }

    public void setNscore(String nscore) {
        this.nscore = nscore;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
