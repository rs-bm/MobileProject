package com.example.mobileproject;

public class Quote
{
    private int id;
    private long missionsWon;
    private long missionsLost;
    private long missionTime;
    private long bugKills;
    private long automationKills;
    private long illuminateKills;
    private long bulletsFired;
    private long bulletsHit;
    private long timePlayed;
    private long deaths;
    private long revives;
    private long friendlyKills;
    private int missionSuccessRate;
    private int accuracy;

    public Quote(int id, long missionsWon, long missionsLost, long missionTime, long bugKills,
                 long automationKills, long illuminateKills, long bulletsFired, long bulletsHit,
                 long timePlayed, long deaths, long revives, long friendlyKills, int missionSuccessRate,
                 int accuracy)
    {
        this.id = id;
        this.missionsWon = missionsWon;
        this.missionsLost = missionsLost;
        this.missionTime = missionTime;
        this.bugKills = bugKills;
        this.automationKills = automationKills;
        this.illuminateKills = illuminateKills;
        this.bulletsFired = bulletsFired;
        this.bulletsHit = bulletsHit;
        this.timePlayed = timePlayed;
        this.deaths = deaths;
        this.revives = revives;
        this.friendlyKills = friendlyKills;
        this.missionSuccessRate = missionSuccessRate;
        this.accuracy = accuracy;
    }
    public int getId()
    {
        return id;
    }
    public long getMissionsWon()
    {
        return missionsWon;
    }
    public long getMissionsLost()
    {
        return missionsLost;
    }
    public long getMissionTime()
    {
        return missionTime;
    }
    public long getBugKills()
    {
        return bugKills;
    }
    public long getAutomationKills()
    {
        return automationKills;
    }
    public long getIlluminateKills()
    {
        return illuminateKills;
    }
    public long getBulletsFired()
    {
        return bulletsFired;
    }
    public long getBulletsHit()
    {
        return bulletsHit;
    }
    public long getTimePlayed()
    {
        return timePlayed;
    }
    public long getDeaths()
    {
        return deaths;
    }
    public long getRevives()
    {
        return revives;
    }
    public long getFriendlyKills()
    {
        return friendlyKills;
    }
    public int getMissionSuccessRate()
    {
        return missionSuccessRate;
    }
    public int getAccuracy()
    {
        return accuracy;
    }

}
