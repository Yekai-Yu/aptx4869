'use strict';

import mongoose from 'mongoose'

const Schema = mongoose.Schema;

const userAuthSchema = new Schema({
    ID: Number,
    Name: String,
    Email: String,
    Password: String
});

userAuthSchema.index({ID:1});

const UserAuth = mongoose.model('UserAuth', userAuthSchema);

export default UserAuth;
