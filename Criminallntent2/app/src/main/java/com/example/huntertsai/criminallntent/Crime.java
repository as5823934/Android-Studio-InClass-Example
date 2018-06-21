package com.example.huntertsai.criminallntent;

import java.security.PublicKey;
import java.util.Date;
import java.util.UUID;

/**
 * Created by huntertsai on 2018-01-31.
 */

public class Crime {
    private UUID mId;
    private String mtitle;
    private Date mDate;
    private boolean mSolved;

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();

    }
}
