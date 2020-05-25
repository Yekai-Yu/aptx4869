'use strict';

import mongoose from 'mongoose'

const Schema = mongoose.Schema;

const restuarantCommentSchema = new Schema({
    ID: Number,
    PostId: Number,
    UserId: Number,
    RestuarantId: Number,
    Comment: String
});

restuarantCommentSchema.index({ID:1});

const RestuarantComment = mongoose.model('RestuarantComment', restuarantCommentSchema);

export default RestuarantComment;
