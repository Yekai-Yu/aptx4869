'use strict';

import mongoose from 'mongoose'

const Schema = mongoose.Schema;

const userSchema = new Schema({
    ID: Number,
    Name: String,
    Preference: [String],
    Rating: {type: Number, default: 0},
    RatingCount: {type: Number, default: 0},
    PostCount: {type: Number, default: 0}
});

userSchema.index({ID:1});

const User = mongoose.model('User', userSchema);

export default User;
