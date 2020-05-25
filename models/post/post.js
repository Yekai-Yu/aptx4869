'use strict';

import mongoose from 'mongoose'

const Schema = mongoose.Schema;

const postSchema = new Schema({
    ID: Number,
    Date: {type: Date, default: Date.now},
    UserId: Number,
    RestuarantId: Number,
    Dishes: [String],
    PriceRange: String,
    MeetingAddress: String,
    MinWantedPPL: Number,
    MaxWantedPPL: Number,
    Participants: [Number],
    CommentsOnRest: [Number],
    RestuarantRating: {type: Number, default: 0}
});

postSchema.index({ID:1});

const Post = mongoose.model('Post', postSchema);

export default Post;
