export interface Artist {
    id: number;
    firstName: string;
    lastName: string;
    description: string;
    imageUrl: string;
}

export interface Event {
    id: number;
    title: string;
    location: string;
    eventDate: string;
    imageUrl: string;
    averageRating: number;

}

export interface Rating {
    id: number;
    stars: number;
    comment: string;
    createdAt: string;
}