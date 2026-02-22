import { Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import EventDetailPage from "./pages/EventDetailPage";
import ArtistsPage from "./pages/ArtistsPage.tsx";
import EventsPage from "./pages/EventsPage.tsx";
import ArtistEventsPage from "./pages/ArtistEventPage.tsx";


function App() {
    return (
        <>
            <Navbar/>
            <Routes>
                <Route path="/" element={<EventsPage />} />
                <Route path="/artists" element={<ArtistsPage />} />
                <Route path="/artists/:id/events" element={<ArtistEventsPage />} />
                <Route path="/events/:id" element={<EventDetailPage />} />
            </Routes>
        </>
    );
}

export default App;
