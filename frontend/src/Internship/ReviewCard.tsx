import { useState } from 'react';
import Typography from '@mui/material/Typography';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import axios from 'axios';
import CommentBox from './CommentBox';
import { useParams } from 'react-router-dom';
import CommentCard from './CommentCard';
import useAuthContext from '../AuthContext';
import Rating from '@mui/material/Rating';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import FavoriteIcon from '@mui/icons-material/Favorite';
import React from 'react';
import { usePostComment } from './hooks';

interface ReviewCardProps{
  id: string;
  userId: string
  datePosted: string;
  numLikes: number;
  numDislikes: number;
  content: string;
  comments: Comment[];
  rating: number;
}

export default function ReviewCard({
  id,
  userId,
  datePosted,
  numLikes,
  numDislikes,
  content,
  comments,
  rating,
}: ReviewCardProps) {
  const { corporateId, internshipId } = useParams();
  const { postComment } = usePostComment();
  const [showComment, setShowComment] = useState(false);
  const [moreComments, setMoreComments] = useState<Comment[]>(comments);
  const [clickedDelete, setClickedDelete] = useState(false);
  const authContext = useAuthContext();
  const principal = authContext.principal;
  const handleDelete = () => {
    setClickedDelete(true);
    axios
      .delete(`/corporates/${corporateId}/internships/${internshipId}/reviews/${id}`, {
        data: {
          internshipId: internshipId,
          reviewId: id,
        },
      })
      .then(() => {
        window.location.reload();
      });
    setTimeout(() => {
      setClickedDelete(false);
    }, 3000);
  };
  const handleShowCommentBox = () => {
    setShowComment(!showComment);
  };

  const handlePost = (comment: string): void => {
    if (!principal || !corporateId || !internshipId) return;
    postComment(corporateId, internshipId, id, 'Review', comment, id)
      .then((result) => {
        if (result.status === 'SUCCESS') {
          setShowComment(false);
          setMoreComments([
            ...moreComments,
            {
              user: principal,
              id: result.id,
              content: result.content,
              comments: [] as Comment[],
              datePosted: result.datePosted,
            } as Comment,
          ]);
        }
      })
      .catch((err) => {});
  };

  return (
    <Box
      sx={{
        background: 'rgba(22, 22, 22, 1)',
        borderRadius: 5,
        my: 3,
        py: 3,
        px: 5,
      }}>
      <Box textAlign='start'>
        <Box sx={{ display: 'flex' }}>
          <Typography variant='h5' sx={{ fontWeight: 'bold' }}>
            {userId}
          </Typography>
          <Paper
            sx={{
              ml: 'auto',
              display: 'inline-flex',
              alignItems: 'center',
              background: 'rgba(35, 36, 35, 0.4)',
              color: 'white',
              px: 2,
              py: 1,
              borderRadius: 3,
            }}
            elevation={5}>
            <Typography sx={{ mr: 2, fontWeight: 'bold' }}>👍 {numLikes}</Typography>
            <Typography sx={{ fontWeight: 'bold' }}>👎 {numDislikes}</Typography>
          </Paper>
        </Box>
        <Rating
          value={rating || 0}
          icon={<FavoriteIcon />}
          emptyIcon={<FavoriteBorderIcon sx={{ color: 'gray' }} />}
          readOnly
        />
        <Box sx={{ ml: 2, my: 1 }}>
          <Typography variant='h6' style={{ wordWrap: 'break-word' }}>
            {content}
          </Typography>
        </Box>
        <Box textAlign={'right'}>
          <Typography sx={{ ml: 'auto' }}>{datePosted.match(/^\d{4}-\d{2}-\d{2}/)}</Typography>
        </Box>
        <Box sx={{ display: 'flex', my: 1 }}>
          <Button
            sx={{ ml: 'auto', mr: 1 }}
            variant='contained'
            onClick={handleShowCommentBox}
            color='primary'
            size='small'>
            comment
          </Button>
          <Button
            variant='contained'
            onClick={handleDelete}
            color='error'
            size='small'
            disabled={clickedDelete}>
            delete
          </Button>
        </Box>
      </Box>
      {showComment ? (
        <Box sx={{ mt: 2 }}>
          <CommentBox
            handleShowCommentBox={handleShowCommentBox}
            handlePost={handlePost}
          />
        </Box>
      ) : undefined}

      {moreComments.length > 0 &&
        moreComments.map((comment, i) => {
          return (
            <CommentCard
              key={i}
              reviewId={id}
              parentType={'Review'}
              commentId={comment.id}
              parentId={id}
              userId={comment.user.username}
              content={comment.content}
              datePosted={comment.datePosted}
              comments={comment.comments}
            />
          );
        })}
    </Box>
  );
}
