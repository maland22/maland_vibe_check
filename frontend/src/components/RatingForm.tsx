import { useState } from "react";
import { Rating, TextField, Button, Box } from "@mui/material";
import { useStore } from "../store/useStore";

import React from 'react';

interface RatingFormProps {
    eventId: number;
}

const RatingForm: React.FC<RatingFormProps> = ({eventId}) => {
    const { addRating, fetchRatings } = useStore();

    const [stars, setStars] = useState<number | null>(0);
    const [comment, setComment] = useState<string>("");

    const handleSubmit = async () => {

        if (!stars) return;

        await addRating(eventId, stars, comment);
        await fetchRatings(eventId);

        setStars(0);
        setComment("");
    };

    return (
        <Box sx={{ mt: 3 }}>
            <Rating
                value={stars}
                onChange={(_, newValue) => setStars(newValue)}
            />

            <TextField
                fullWidth
                label="Comment"
                sx={{ mt: 2 }}
                value={comment}
                onChange={(e) => setComment(e.target.value)}
            />
            <Button
                variant="contained"
                sx={{ mt: 2 }}
                onClick={handleSubmit}
            >
                Submit Rating
            </Button>
        </Box>
    );
};

export default RatingForm;