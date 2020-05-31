'use strict';

const mongoose = require('mongoose');
const AutoIncrement = require('mongoose-sequence')(mongoose);

const Schema = mongoose.Schema;

const userSchema = new Schema({
    ID: Number,
    Name: String,
    Preference: [String],
    Rating: {type: Number, default: 0},
    RatingCount: {type: Number, default: 0},
    PostCount: {type: Number, default: 0}
});

userSchema.index({ID: 1});
userSchema.plugin(AutoIncrement, {inc_field: 'ID', start_seq: 100});

const User = mongoose.model('User', userSchema);

module.exports = User;
