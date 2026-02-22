import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import {
    Typography,
    Card,
    CardContent,
    CardMedia,
    Box,
    Rating,
    Divider
} from "@mui/material";
import { useStore } from "../store/useStore";
import RatingForm from "../components/RatingForm";

const EventDetailPage: React.FC = () => {

    const { id } = useParams();
    const eventId = Number(id);

    const { events, ratings, fetchRatings } = useStore();

    const event = events.find(e => e.id === eventId);

    useEffect(() => {
        if (eventId) {
            fetchRatings(eventId);
        }
    }, [eventId]);

    const average =
        ratings.length > 0
            ? ratings.reduce((sum, r) => sum + r.stars, 0) / ratings.length
            : 0;

    if (!event) return <Typography>Event not found</Typography>;

    return (
        <Box sx={{ p: 3 }}>

            <Card sx={{ mb: 4 }}>
                <CardMedia
                    component="img"
                    height="300"
                    image={event.imageUrl}
                    alt={event.title}
                />

                <CardContent>
                    <Typography variant="h4">
                        {event.title}
                    </Typography>

                    <Typography>
                        📍 {event.location}
                    </Typography>

                    <Typography>
                        📅 {new Date(event.eventDate).toLocaleDateString()}
                    </Typography>

                    <Box sx={{ mt: 2 }}>
                        <Rating value={average} precision={0.5} readOnly />
                        <Typography>
                            ({ratings.length} ratings)
                        </Typography>
                    </Box>
                </CardContent>
            </Card>

            <Typography variant="h5" sx={{ mb: 2 }}>
                Ratings
            </Typography>

            {ratings.map((rating) => (
                <Box key={rating.id} sx={{ mb: 2 }}>
                    <Rating value={rating.stars} readOnly />
                    <Typography>{rating.comment}</Typography>
                    <Divider sx={{ mt: 1 }} />
                </Box>
            ))}

            <RatingForm eventId={eventId} />
        </Box>
    );
};

export default EventDetailPage;