import React from 'react';

import { Card, CardContent, CardMedia, Typography, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";
import type {Event} from "../common/model.ts";

interface EventCardProps {
    event: Event;
    index: number;
}

const EventCard: React.FC<EventCardProps> = ({ event }) => {
    const navigate = useNavigate();

    const handleClick = () => {
        navigate(`/events/${event.id}`);
    };

    return (
        <Card sx={{ height: "100%", display: "flex", flexDirection: "column" }}>
            <CardMedia
                component="img"
                image={event.imageUrl}
                alt={event.title}
                sx={{
                    height: 200,
                    objectFit: "cover"
                }}
            />
            <CardContent>
                <Typography variant="h6">
                    {event.title}
                </Typography>

                <Typography variant="body2">
                    📍 {event.location}
                </Typography>

                <Typography variant="body2">
                    📅 {new Date(event.eventDate).toLocaleDateString()}
                </Typography>
                <Typography variant="body2">
                    ⭐ {event.averageRating?.toFixed(1) ?? "No ratings"}
                </Typography>

                <Button
                    sx={{
                        mt: 2,
                        backgroundColor: "white",
                        color: "black",
                        border: "1px solid black",
                        "&:hover": {
                            backgroundColor: "black",
                            color: "white"
                        }
                    }}
                    onClick={handleClick}
                >
                    View Details
                </Button>
            </CardContent>
        </Card>
    );
};

export default EventCard;