'use strict';

import mongoose from 'mongoose'

const Schema = mongoose.Schema;

const userCommentSchema = new Schema({
    ID: Number,
    PostId: Number,
    CommenterId: Number,
    CommenteeId: Number,
    Comment: String
});

userCommentSchema.index({ID:1});

const UserComment = mongoose.model('UserComment', userCommentSchema);

export default UserComment;
