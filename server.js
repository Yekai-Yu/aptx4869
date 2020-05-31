const express = require('express');
const router = require('./routes/index');

const app = express();

router.router(app);
const port = 3000;

// app.get('/', (req, res) => res.send("Hello world!"));

app.listen(port, () => console.log(`Example app`));
